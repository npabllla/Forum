package ru.job4j.forum.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Answer> answers;

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public void deleteAnswer(Answer answer) {
        this.answers.remove(answer);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return id == comment.id
                && Objects.equals(author, comment.author)
                && Objects.equals(post, comment.post)
                && Objects.equals(content, comment.content)
                && Objects.equals(created, comment.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, post, content, created);
    }
}
