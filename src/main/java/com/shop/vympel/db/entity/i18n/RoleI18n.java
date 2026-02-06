package com.shop.vympel.db.entity.i18n;

import com.shop.vympel.db.entity.auth.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "role_i18n")
public class RoleI18n {
    @EmbeddedId
    private RoleI18nId id;

    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;


}