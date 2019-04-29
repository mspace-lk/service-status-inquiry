package hms.tap.servicestatusinquiry.service;

import hms.tap.sdk.commons.MessageEncoding;
import hms.tap.sdk.sms.*;
import hms.tap.servicestatusinquiry.domain.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PlatformRequestHandlerServiceImpl implements PlatformRequestHandlerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlatformRequestHandlerServiceImpl.class);

    @Autowired
    private DocumentService documentService;
    @Autowired
    private SmsMoReceiver smsMoReceiver;
    @Autowired
    private SmsMtSender smsMtSender;

    @Override
    public SMSMOResponse SmsReceive(SMSMOIndication smsmoIndication) {
        LOGGER.debug("Received SmsMoRequest[{}]", smsmoIndication);

        String requestedMsg = smsmoIndication.getMessage();

        String sourceAddress = smsmoIndication.getSourceAddress();

        boolean isValid = isValidMessage(requestedMsg);

        if (isValid) {
            LOGGER.debug("Received message is in a proper format");

            Optional<Document> documentReqOpt = documentService.findAllDocumentRequestByRefNumber(requestedMsg);

            String message = retrieveMtMessage(requestedMsg, documentReqOpt);

            SMSMTRequest smsmtRequest = constructSmsMtRequest(smsmoIndication, sourceAddress, message);

            SMSMTConfirmation smsMtConfirmation = smsMtSender.sendSmsToPlatform(smsmtRequest);

            if (smsMtConfirmation.getStatusCode().equalsIgnoreCase("S1000")) {
                LOGGER.debug("SMS-Mt Request is Successful [{}]", smsMtConfirmation);
            }
        } else {
            LOGGER.debug("Received Message is not in a proper format");
        }

        return smsMoReceiver.processReceivedMsg(smsmoIndication, isValid);
    }

    private String retrieveMtMessage(String requestedMsg, Optional<Document> documentReqOpt) {
        if (documentReqOpt.isPresent()) {
            Document document = documentReqOpt.get();
            String requestedDocumentStatus = document.getStatus();
            return mtMessage(requestedMsg, requestedDocumentStatus);
        } else {
            return String.format("Could not find the document request for ref-no[%s]", requestedMsg);
        }
    }

    private SMSMTRequest constructSmsMtRequest(SMSMOIndication smsmoIndication,
                                               String sourceAddress,
                                               String message) {
        return SMSApi
                .newMtSMS()
                .to(smsmoIndication.getSourceAddress())
                .encoding(MessageEncoding.BINARY)
                .from(sourceAddress)
                .forPlatform("platform1")
                .message(message)
                .create();
    }

    private String mtMessage(String referenceNo, String documentRequestStatus) {

        return String
                .format("Your request [%s] is currently in [%s] status", referenceNo, documentRequestStatus);
    }

    private boolean isValidMessage(String message) {
        return Objects.nonNull(message) && !message.trim().isEmpty();
    }
}
