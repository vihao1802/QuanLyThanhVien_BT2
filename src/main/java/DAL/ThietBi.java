/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package DAL;
import java.util.List;
import lombok.Data;
import javax.persistence.*;

/**
 *
 * @author ADMIN
 */
@Data
@Entity
@Table(name = "thietbi") // should equal to table name in db
public class ThietBi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaTB;
    
    @Column(nullable = false, length = 100)
    private String TenTB;
    
    @Column
    private String MoTaTB;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "thietbi" )
    private List<ThongTinSD> listThongTinSD;
    
}
