package hms.tap.servicestatusinquiry.mapper;

import hms.tap.servicestatusinquiry.domain.Document;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentRowMapper implements RowMapper<Document> {

        public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
            Document document = new Document();
            document.setRefNo(rs.getString("ref_no"));
            document.setFullName(rs.getString("full_name"));
            document.setAddress(rs.getString("address"));
            document.setNic(rs.getString("nic"));
            document.setStatus(rs.getString("status"));
            document.setCreateDate(rs.getDate("created_date"));
            document.setLastUpdateDate(rs.getDate("last_updated_date"));
            return document;
        }
}

