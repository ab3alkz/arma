package kz.arma.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by www on 12.03.2017.
 */
@Entity
@Table(name = "SLIDE")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Slide.findAll", query = "SELECT g FROM Slide g"),
        @NamedQuery(name = "Slide.findByIdGame", query = "SELECT g FROM Slide g WHERE g.idGame = :idGame"),
        @NamedQuery(name = "Slide.findById", query = "SELECT g FROM Slide g WHERE g.id = :id")
})
public class Slide {

    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "text")
    private String text;
    @Basic
    @Lob
    @Column(name = "audio")
    private byte[] audio;
    @Basic
    @Lob
    @Column(name = "video")
    private byte[] video;
    @Basic
    @Lob
    @Column(name = "img")
    private byte[] img;
    @Basic
    @Column(name = "answer")
    private String answer;
    @Basic
    @Column(name = "ord")
    private int ord;
    @Basic
    @Column(name = "id_game")
    private String idGame;

    public Slide() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getAudio() {
        return audio;
    }

    public void setAudio(byte[] audio) {
        this.audio = audio;
    }

    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }

    public String getIdGame() {
        return idGame;
    }

    public void setIdGame(String idGame) {
        this.idGame = idGame;
    }
}
