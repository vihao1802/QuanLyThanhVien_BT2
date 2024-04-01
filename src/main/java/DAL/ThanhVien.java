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
public class ThanhVien {
    @Id
    private int MaTV;
    
    @Column
    private String HoTen;
    
    @Column
    private String Khoa;
    
    @Column
    private String Nganh;
    
    @Column
    private String SDT;
    
    @OneToMany(mappedBy = "thanhvien")
    private List<ThongTinSD> listThongTinSD;
    
    @OneToMany(mappedBy = "thanhvien")
    private List<XuLy> listXuLy;

}
