/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package DAL;
import java.util.Date;
import lombok.Data;
import javax.persistence.*;

/**
 *
 * @author ADMIN
 */
@Data
@Entity
@Table
public class ThongTinSD {
    @Id
    private int MaTT;
    
    @Column
    private Date TGVao;
    
    @Column
    private Date TGMuon;
    
    @Column
    private Date TGTra;
    
    @ManyToOne
    @JoinColumn(name = "MaTB")
    private ThietBi thietbi;
    
    @ManyToOne
    @JoinColumn(name = "MaTV")
    private ThanhVien thanhvien;
}
