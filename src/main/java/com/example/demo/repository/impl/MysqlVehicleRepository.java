package com.example.demo.repository.impl;

import com.example.demo.domain.po.VehicleInformationPO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/12 14:46
 * @Version 1.0
 */
@Mapper
public interface MysqlVehicleRepository {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("<script>INSERT INTO `vehicle_information`(vehicle_plate," +
            "vehicle_brand, registration_date, vehicle_color, purchase_date," +
            "purchase_price, vehicle_note) VALUES(" +
            "#{vehiclePlate}, #{vehicleBrand}, #{registrationDate}, " +
            "#{vehicleColor}, #{purchaseDate}, #{purchasePrice}, #{vehicleNote}" +
            ")</script>")
    Long save(VehicleInformationPO vehicleInformationPO);

    @Delete("DELETE FROM `vehicle_information` WHERE id = #{id}")
    void remove(Long id);

    @Update("<script> UPDATE `vehicle_information` <set>" +
            "<if test='vehiclePlate!=null'>vehicle_plate = #{vehiclePlate}, </if>" +
            "<if test='vehicleBrand!=null'>vehicle_brand = #{vehicleBrand}, </if>" +
            "<if test='registrationDate!=null'>registration_date = #{registrationDate}, </if>" +
            "<if test='vehicleColor!=null'>vehicle_color = #{vehicleColor}, </if>" +
            "<if test='purchaseDate!=null'>purchase_date = #{purchaseDate}, </if>" +
            "<if test='purchasePrice!=null'>purchase_price = #{purchasePrice}, </if>" +
            "<if test='vehicleNote!=null'>vehicle_note = #{vehicleNote}, </if>" +
            "<if test='saleitemId!=null'>saleitem_id = #{saleitemId}, </if>" +
            "</set> WHERE id = #{id}</script>")
    void update(VehicleInformationPO vehicleInformationPO);

    @Select("SELECT * FROM `vehicle_information` WHERE id = #{id}")
    @Results({
            @Result(property = "saleItem", column = "id",
                    one = @One(select = "com.example.demo.repository.SaleItemRepository.findSaleItemByVehicleId")),
            @Result(property = "partners", column = "id",
                    many = @Many(select = "com.example.demo.repository.PartnerRepository.findPartnersByVehicleId")),
            @Result(property = "preparednesses", column = "id",
                    many = @Many(select = "com.example.demo.repository.PreparednessRepository.findPreparednessByVehicleId"))
    })
    @Result(property = "id", column = "id")
    VehicleInformationPO find(Long id);

    @Select("SELECT * FROM `vehicle_information`")
    @Results({
            @Result(property = "saleItem", column = "id",
                    one = @One(select = "com.example.demo.repository.SaleItemRepository.findSaleItemByVehicleId")),
            @Result(property = "partners", column = "id",
                    many = @Many(select = "com.example.demo.repository.PartnerRepository.findPartnersByVehicleId")),
            @Result(property = "preparednesses", column = "id",
                    many = @Many(select = "com.example.demo.repository.PreparednessRepository.findPreparednessByVehicleId"))
    })
    @Result(property = "id", column = "id")
    List<VehicleInformationPO> list();

    @Select("SELECT * FROM vehicle_information WHERE vehicle_plate like \"%\" #{vehiclePlate} \"%\"")
    List<VehicleInformationPO> search(String vehiclePlate);

}