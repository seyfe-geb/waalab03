package net.seyfe.waalab03.repo;

import net.seyfe.waalab03.domain.Post;
import net.seyfe.waalab03.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends CrudRepository<Post, Long> {
    public List<Post> findAll();

    public List<Post> findPostByAuthor(String author);

    public List<Post> findPostByTitle(String title);

//    @Query(value = "SELECT p FROM Post p WHERE p.title = :title")
//    public List<Post> getPostsThatMatchATitle(String title);

}
