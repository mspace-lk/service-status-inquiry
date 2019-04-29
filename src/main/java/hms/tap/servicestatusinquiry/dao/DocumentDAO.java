package hms.tap.servicestatusinquiry.dao;

import hms.tap.servicestatusinquiry.domain.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentDAO {

    void saveDocumentDetails(Document document);

    List<Document> findAllDocumentDetails();

    Optional<Document> findDocumentByRefNumber(String refNumber);

    Document updateDocumentByRefNumber(Document document);
}
