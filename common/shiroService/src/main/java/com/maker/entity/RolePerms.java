package com.maker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)//链式调用
@AllArgsConstructor
@NoArgsConstructor
public class RolePerms implements Serializable {

    private Integer roleId;

    private Integer permissionId;


}
