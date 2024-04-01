/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package DAL;

import lombok.Data;
import java.util.Date;
import javax.persistence.*;
/**
 *
 * @author ADMIN
 */
@Data
@Entity
@Table
public class XuLy {
    @Id
    private int MaXL;
    
    @Column
    private String HinhThucXL;
    
    @Column
    private int SoTien;
    
    @Column
    private Date NgayXL;
    
    @Column
    private int TrangThaiXL;
    
    @ManyToOne
    @JoinColumn(name = "MaTV")
    private ThanhVien thanhvien;
}
