package hms.tap.servicestatusinquiry.service;

import hms.tap.sdk.sms.SMSApi;
import hms.tap.sdk.sms.SMSMOIndication;
import hms.tap.sdk.sms.SMSMOResponse;
import org.springframework.stereotype.Service;

@Service
public class SmsMoReceiverImpl implements SmsMoReceiver {
    @Override
    public SMSMOResponse processReceivedMsg(SMSMOIndication smsMoRequest, boolean isValid) {
        if (isValid) {
            return SMSApi.newSmsMOResponse("S1000", "SUCCESS");
        } else {
            return SMSApi.newSmsMOResponse("E1000", "Invalid Request Message");
        }
    }
}
