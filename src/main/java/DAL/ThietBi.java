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
@Table
public class ThietBi {
    @Id
    private int MaTB;
    
    @Column
    private String TenTB;
    
    @Column
    private String MoTaTB;
    
    @OneToMany(mappedBy = "thietbi" )
    private List<ThongTinSD> listThongTinSD;
    
}
