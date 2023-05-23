package SpringTermo.termo.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import SpringTermo.termo.model.TermoModel;

@Repository
public class Daotermo {
	@Autowired
	GenericDao ge;
	public void Inserir(TermoModel tm) throws SQLException, ClassNotFoundException {
		Connection c = ge.getC();
		String sql = "INSERT INTO BME280 VALUES (GETDATE(),?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setDouble(1, tm.getpRESSURE());
		ps.setDouble(2, tm.gethUMIDITY());
		ps.setDouble(3, tm.gettEMPERATURE());
		ps.execute();
		ps.close();
	}
	public void Atualizar(TermoModel tm) throws SQLException, ClassNotFoundException {
		Connection c = ge.getC();
		StringBuffer sb1 = new StringBuffer();
		sb1.append("UPDATE BME280 ");
		sb1.append("SET PRESSURE = ?,HUMIDITY = ?,TEMPERATURE = ? ");
		sb1.append("WHERE CONVERT(VARCHAR(MAX),BME280.LDT,120) =  ");
		sb1.append("(SUBSTRING(?,1,LEN(?)-4))");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setDouble(1, tm.getpRESSURE());
		ps.setDouble(2, tm.gethUMIDITY());
		ps.setDouble(3, tm.gettEMPERATURE());
		ps.setString(3, tm.getlDT());
		ps.setString(4, tm.getlDT());
		ps.execute();
		ps.close();
	}
	public void Excluir(TermoModel tm) throws SQLException, ClassNotFoundException {
		Connection c = ge.getC();
		StringBuffer sb1 = new StringBuffer();
		sb1.append("DELETE BME280 ");
		sb1.append("WHERE CONVERT(VARCHAR(MAX),BME280.LDT,120) =  ");
		sb1.append("(SUBSTRING(?,1,LEN(?)-4))  ");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setString(1, tm.getlDT());
		ps.setString(2, tm.getlDT());
		ps.execute();
		ps.close();
	}
	public TermoModel Busca(TermoModel tm) throws SQLException, ClassNotFoundException {
		Connection c = ge.getC();
		StringBuffer sb1 = new StringBuffer();
		sb1.append("SELECT PRESSURE,HUMIDITY,TEMPERATURE FROM BME280 BM  ");
		sb1.append("WHERE CONVERT(VARCHAR(MAX),BM.LDT,120) =  ");
		sb1.append("(SUBSTRING(?,1,LEN(?)-4))");
		PreparedStatement ps = c.prepareStatement(sb1.toString());
		ps.setString(1, tm.getlDT());
		ps.setString(2, tm.getlDT());
		ResultSet rs = ps.executeQuery();
		TermoModel tms = new TermoModel();
		if(rs.next()) {
			tms.sethUMIDITY(rs.getDouble("HUMIDITY"));
			tms.setpRESSURE(rs.getDouble("PRESSURE"));
			tms.settEMPERATURE(rs.getDouble("TEMPERATURE"));
			tms.setlDT(rs.getDate("LDT").toString());
			
		}
		return tms;
	}
	public List<TermoModel> Lista() throws SQLException, ClassNotFoundException{
		Connection c = ge.getC();
		String sql = "SELECT LDT,PRESSURE,HUMIDITY,TEMPERATURE FROM BME280";
		List<TermoModel> lm = new ArrayList<>();
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			TermoModel tms = new TermoModel();
			tms.sethUMIDITY(rs.getDouble("HUMIDITY"));
			tms.setpRESSURE(rs.getDouble("PRESSURE"));
			tms.settEMPERATURE(rs.getDouble("TEMPERATURE"));
			tms.setlDT(rs.getDate("LDT").toString());
			lm.add(tms);
		}
		return lm;
	}
}
