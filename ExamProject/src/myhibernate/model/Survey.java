package myhibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "table1")
public class Survey implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int surveyID;
	
	@Column(name = "username")
    private String username;
	
	@Column(name = "game")
    private String game;
	
	@Column(name = "genre")
    private String genre;
	
	@Column(name = "playedtime")
    private String playedtime;
	
	@Column(name = "description")
    private String description;
	
	@Column(name = "likes")
    private String likes;
	
	@Column(name = "dislikes")
    private String dislikes;
	
	@Column(name = "recommend")
    private String recommend;
	
	public String getUserName() {
        return username;
    }
    public void setUserName(String username) {
        this.username = username;
    }
    
    public String getGame() {
        return game;
    }
    public void setGame(String game) {
        this.game = game;
    }
    
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public String getPlayedTime() {
        return playedtime;
    }
    public void setPlayedTime(String playedtime) {
        this.playedtime = playedtime;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getLikes() {
        return likes;
    }
    public void setLikes(String likes) {
        this.likes = likes;
    }
    
    public String getDislikes() {
        return dislikes;
    }
    public void setDislikes(String dislikes) {
        this.dislikes = dislikes;
    }
    
    public String getRecommend() {
        return recommend;
    }
    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}
