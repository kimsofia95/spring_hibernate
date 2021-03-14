package hiber.model;

import com.sun.org.glassfish.gmbal.ManagedObject;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name = "model")
    private String model;
    @Column(name = "series")
    private int series;
    @OneToOne(mappedBy = "car")
    private User user;

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public Car() {

    }

    public User getUser() {
        return user;
    }
    
    public String getModel() {
        return model;
    }

    public int getSeries() {
        return series;
    }
}
