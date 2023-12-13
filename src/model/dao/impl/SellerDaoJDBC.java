package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
  private Connection conn;

  public SellerDaoJDBC(Connection conn) {
    this.conn = conn;
  }

  @Override
  public void deleteById(Integer id) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<Seller> findByAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Seller findById(Integer id) {
    PreparedStatement st = null;
    ResultSet rs = null;
    try {
      st = conn.prepareStatement(
          "SELECT seller.*,department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE seller.Id = ?");
      st.setInt(1, id);
      // retorna em formato tabela, posição 0 não contém objeto
      rs = st.executeQuery();

      // check se veio algum objeto
      if (rs.next()) {
        Department department = instantiateDepartment(rs);
        Seller seller = instantiateSeller(rs, department);

        return seller;
      }

      return null;
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DB.closeStatement(st);
      DB.closeResultSet(rs);
    }
  }

  public List<Seller> findByDepartment(Department department) {
    PreparedStatement st = null;
    ResultSet rs = null;

    try {
      st = conn.prepareStatement(
        "SELECT seller.*,department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE DepartmentId = ? ORDER BY Name"
      );
      st.setInt(1, department.getId());
      rs = st.executeQuery();

      Map<Integer, Department> map = new HashMap<>();

      List<Seller> list = new ArrayList<>();
      while (rs.next()) {
        Department dep = map.get(rs.getInt("DepartmentId"));
        if (dep == null) {
          dep = instantiateDepartment(rs);
          map.put(rs.getInt("DepartmentId"), dep);
        }

        Seller seller = instantiateSeller(rs, dep);
        list.add(seller);
      }

      return list;
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    }
  }

  @Override
  public void insert(Seller obj) {
    // TODO Auto-generated method stub

  }

  @Override
  public void update(Seller obj) {
    // TODO Auto-generated method stub

  }

  public Department instantiateDepartment(ResultSet rs) throws SQLException {
    return new Department(
        rs.getInt("DepartmentId"),
        rs.getString("DepName"));
  }

  public Seller instantiateSeller(ResultSet rs, Department department) throws SQLException {
    return new Seller(
      rs.getInt("Id"),
      rs.getString("Name"),
      rs.getString("Email"),
      rs.getDate("BirthDate"),
      rs.getDouble("BaseSalary"),
      department
    );
  }
}
