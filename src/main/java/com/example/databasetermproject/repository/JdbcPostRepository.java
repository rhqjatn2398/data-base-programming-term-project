package com.example.databasetermproject.repository;

import com.example.databasetermproject.controller.SearchForm;
import com.example.databasetermproject.domain.Post;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostRepository implements PostRepository {
    private final DataSource dataSource;

    public JdbcPostRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Post save(Post post) {
        String sql = "insert into post(uid, post_title, post_content, area, phone, category) values(?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, post.getUid());
            pstmt.setString(2, post.getTitle());
            pstmt.setString(3, post.getContent());
            pstmt.setString(4, post.getArea());
            pstmt.setString(5, post.getPhone());
            pstmt.setString(6, post.getCategory());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if(rs.next()) {
                post.setId(rs.getInt(1));
            } else {
                throw new SQLException("작성 실패");
            }
            return post;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Post> find(SearchForm searchForm) {
        String sql = "select * from post where 1";
        if(searchForm.getArea() != "") {
            sql += " and area like '%" + searchForm.getArea() + "%'";
        }
        if(searchForm.getCategory() != "") {
            sql += " and category like '%" + searchForm.getCategory() + "%'";
        }
        if(searchForm.getContent() != "") {
            sql += " and post_content like '%" + searchForm.getContent() + "%'";
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            List<Post> posts = new ArrayList<>();

            while(rs.next()) {
                Post post = new Post();
                post.setTitle(rs.getString("post_title"));
                post.setContent(rs.getString("post_content"));
                post.setArea(rs.getString("area"));
                post.setPhone(rs.getString("phone"));
                post.setCategory(rs.getString("category"));
                post.setUid(rs.getLong("uid")); // 실제로는 범수 db에서 select 해서 가져와야 함
                posts.add(post);
            }

            return posts;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if(rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(conn != null) {
                close(conn);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
