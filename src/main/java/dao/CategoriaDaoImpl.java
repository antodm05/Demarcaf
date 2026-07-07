package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.CategoriaBean;

public class CategoriaDaoImpl implements CategoriaDao {

    private DataSource connessioneDB;

    public CategoriaDaoImpl(DataSource ds) {
        this.connessioneDB = ds;
    }

    @Override
    public List<CategoriaBean> doRetrieveAll() throws SQLException {
//seleziono tutti 
        List<CategoriaBean> listaCategorie = new ArrayList<CategoriaBean>();
        String sql = "SELECT * FROM categoria";

        try (Connection conn = connessioneDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CategoriaBean categoria = new CategoriaBean();
                    categoria.setIdCategoria(rs.getInt("id_categoria"));
                    categoria.setNome(rs.getString("nome"));
                    listaCategorie.add(categoria);
                }
            }
        }
        return listaCategorie;
    }
}