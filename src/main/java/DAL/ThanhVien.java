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
@Table(name =  "thanhvien") // should equal to table name in db
public class ThanhVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaTV;
    
    @Column(nullable = false, length = 100)
    private String HoTen;
    
    @Column(length = 100)
    private String Khoa;
    
    @Column(length = 100)
    private String Nganh;
    
    @Column(length = 15)
    private String SDT;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "thanhvien")
    private List<ThongTinSD> listThongTinSD;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "thanhvien")
    private List<XuLy> listXuLy;

}
