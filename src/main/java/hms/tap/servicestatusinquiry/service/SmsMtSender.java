package hms.tap.servicestatusinquiry.service;

import hms.tap.sdk.sms.SMSMTConfirmation;
import hms.tap.sdk.sms.SMSMTRequest;

public interface SmsMtSender {
    SMSMTConfirmation sendSmsToPlatform(SMSMTRequest smsmtRequest);
}
