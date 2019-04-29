package hms.tap.servicestatusinquiry.dao;

import hms.tap.servicestatusinquiry.domain.Document;
import hms.tap.servicestatusinquiry.mapper.DocumentRowMapper;
import hms.tap.servicestatusinquiry.service.PlatformRequestHandlerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class DocumentDAOImpl implements DocumentDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlatformRequestHandlerServiceImpl.class);
    private static final String CREATE_DOCUMENT = "INSERT INTO document(ref_no,full_name,address,nic,status,created_date,last_updated_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_ALL_DOCUMENTS ="SELECT * FROM document order by id desc";
    private static final String FIND_DOCUMENT_BY_REF_NO = "SELECT * FROM document WHERE ref_no = ?";
    private static final String UPDATE_DOCUMENT_BY_REF_NO = "UPDATE document SET status=? ,last_updated_date=? WHERE ref_no=?";
    private final JdbcTemplate template;

    @Autowired
    public DocumentDAOImpl(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    @Override
    public void saveDocumentDetails(Document document) {
        LOGGER.debug("Creating DB Entry for Document [{}]", document);
        try {
            template.update(CREATE_DOCUMENT,
                    new Object[]{
                            document.getRefNo(), document.getFullName(),
                            document.getAddress(), document.getNic(),
                            "PENDING", document.lastUpdateDate(), document.lastUpdateDate()
                    });
        } catch (Exception e) {
            LOGGER.error("Error occurred while inserting data into the table ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Document> findAllDocumentDetails() {
        LOGGER.debug("Finding All Documents");
        try {
            List<Document> documents = template.query(FIND_ALL_DOCUMENTS,
                    new DocumentRowMapper());
            return documents;
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while finding all passport-request ", e);
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Document> findDocumentByRefNumber(String refNumber) {
        LOGGER.debug("Finding Document Details for Ref[{}]", refNumber);
        try {
            Document document = template.queryForObject(FIND_DOCUMENT_BY_REF_NO,
                    new Object[]{refNumber}, new DocumentRowMapper());
            return Optional.ofNullable(document);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("Could not find any passport-request with ref-no[{}]", refNumber);
            return Optional.empty();
        } catch (Exception e) {
            LOGGER.error("Error occurred while finding passport request with ref-no[{}] ", refNumber, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Document updateDocumentByRefNumber(Document document) {
        LOGGER.debug("Updating Document Details for Ref[{}]", document.getRefNo());
        try {
            template.update(UPDATE_DOCUMENT_BY_REF_NO,
                    document.getStatus(), document.lastUpdateDate(), document.getRefNo());
        }catch (DataAccessException e){
            LOGGER.error("Error occurred while updating status", e);
        }
        return document;
    }
}



