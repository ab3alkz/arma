package kz.arma.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by www on 11.03.2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Games.findAll", query = "SELECT d FROM Games d"),
        @NamedQuery(name = "Games.findById", query = "SELECT d FROM Games d WHERE d.id = :id")
})
public class Games {
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "date_game")
    private Date dateGame;
    @Basic
    @Column(name = "date_add")
    private Date dateAdd;
    @Basic
    @Column(name = "archive")
    private Integer archive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateGame() {
        return dateGame;
    }

    public void setDateGame(Date dateGame) {
        this.dateGame = dateGame;
    }

    public Integer getArchive() {
        return archive;
    }

    public void setArchive(Integer archive) {
        this.archive = archive;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Games() {
    }
}
