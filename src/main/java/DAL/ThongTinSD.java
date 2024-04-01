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
@Table(name =  "thongtinsd") // should equal to table name in db
public class ThongTinSD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaTT;
    
    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date TGVao;
    
    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date TGMuon;
    
    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date TGTra;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTB")
    private ThietBi thietbi;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTV")
    private ThanhVien thanhvien;
    
    @Override
    public String toString() {
        return "ThongTinSD{" +
                "MaTT=" + MaTT + "," + 
                "TGVao=" + TGVao + "," + 
                "TGMuon=" + TGMuon + "," + 
                "TGTra=" + TGTra + "," +
                "MoTaTB" + thietbi.getMoTaTB() + "," +
                "TenTB=" + thietbi.getTenTB() + "," +
                "thanhvien=" + thanhvien.getHoTen() +
                '}';
    }
}
