package com.jspproject.host.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jspproject.dto.ReviewDto;

public class HostReviewListDao {
	DataSource dataSource;
	public HostReviewListDao() {
		try {
			Context context = new InitialContext();	// javax.naming
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/camping");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ReviewDto>reviewList(int hSeq){
		ArrayList<ReviewDto> dtos = new ArrayList<ReviewDto>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select rv.rvSeq, reg.regName, rv.rvCId, rv.rvCName, rv.rvTitle, rv.rvTime ";
			String query2 = "from review rv inner join regcamp reg ";
			String query3 = "on rv.regcamp_regSeq = reg.regSeq where reg.host_hSeq = ?";
			ps = connection.prepareStatement(query + query2 + query3);
			rs = ps.executeQuery();
			
			ps.setInt(1, hSeq);
			
			while(rs.next()) {
				int rvSeq = rs.getInt("rvSeq");
				String regName = rs.getString("regName");
				String rvCId = rs.getString("rvCId");
				String rvCName = rs.getString("rvCName");
				String rvTitle = rs.getString("rvTitle");
				Timestamp rvTime = rs.getTimestamp("rvTime");
				
				ReviewDto dto = new ReviewDto(rvSeq, regName, rvCId, rvCName, rvTitle, rvTime);
				dtos.add(dto);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}

}
