package hms.tap.servicestatusinquiry.service;

import hms.tap.servicestatusinquiry.domain.Document;

import java.util.List;
import java.util.Optional;


public interface DocumentService {
    Document createDocumentRequest(Document document);

    List<Document> findAllDocumentRequest();

    Optional<Document> findAllDocumentRequestByRefNumber(String refNumber);

    Document updateDocumentRequestByRefNumber(String refNo, Document document);
}

