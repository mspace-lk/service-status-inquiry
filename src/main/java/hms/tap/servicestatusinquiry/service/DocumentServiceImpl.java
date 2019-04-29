package hms.tap.servicestatusinquiry.service;

import hms.tap.servicestatusinquiry.dao.DocumentDAO;
import hms.tap.servicestatusinquiry.domain.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentDAO documentDAO;

    @Override
    public Document createDocumentRequest(Document document) {
        documentDAO.saveDocumentDetails(document);
        return document;
    }

    @Override
    public List<Document> findAllDocumentRequest() {
        return documentDAO.findAllDocumentDetails();
    }

    @Override
    public Optional<Document> findAllDocumentRequestByRefNumber(String refNumber) {
        return documentDAO.findDocumentByRefNumber(refNumber);
    }

    @Override
    public Document updateDocumentRequestByRefNumber(String refNO, Document document) {
        document.setRefNo(refNO);
        return documentDAO.updateDocumentByRefNumber(document);
    }
}

