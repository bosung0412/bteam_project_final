package kr.co.bteam.bteam_pro.login.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "FAQBoard")
public class FAQBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ONO;

    @Column(nullable = false)
    private Long Member_No;

    private String oname;
    private String ocontent;
    private LocalDateTime oregdate;
    private String ocategory;
    private String ocentent;
}
