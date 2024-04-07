package DAL;
import lombok.Data;
import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@Table(name = "xuly")
public class XuLy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaXL;


    @Column(length = 250)
    private String HinhThucXL;
    
    @Column
    private Integer SoTien;
    
    @Column
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date NgayXL;
    
    @Column
    private int TrangThaiXL;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTV")
    private ThanhVien thanhvien;
    
    @Override
    public String toString() {
        return "ThongTinSD{" +
                "MaXL=" + MaXL + "," + 
                "HinhThucXL=" + HinhThucXL + "," + 
                "NgayXL=" + NgayXL + "," +
                "thanhvien=" + thanhvien.getHoTen() +
                '}';
    }
}
