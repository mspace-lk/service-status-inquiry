package hms.tap.servicestatusinquiry.service;

import hms.tap.sdk.sms.SMSMOIndication;
import hms.tap.sdk.sms.SMSMOResponse;

public interface SmsMoReceiver {
    SMSMOResponse processReceivedMsg(SMSMOIndication smsMoRequest, boolean isValid);
}
