create database Do_An_De_04
use Do_An_De_04
select * from SanPham
delete SanPham
sp_help tblSanPham


--NHANVIEN(MaNV, Hoten, DiaChi, SDT, NgaySinh, GT, Luong) 
	create table NhanVien
	(
		MaNV varchar(10) primary key, 
		Hoten nvarchar(50) not null, 
		DiaChi nvarchar(50) not null, 
		SDT varchar(12) unique, 
		NgaySinh date check (ngaysinh<getdate()) not null , 
		GT nvarchar(3) not null, 
		Luong int not null
	)
--SANPHAM(MaSP, TenSP, NSX, HSD, LoaiSP, SoLuong, GiaBan) 
	CREATE TABLE SanPham
	(
		MaSP varchar(10) primary key,
		TenSP nvarchar(50) not null,
		NhaSX nvarchar(50) not null,
		LoaiSP nvarchar(50) not null,
		SoLuong int not null,
		DonViTinh nvarchar(50) not null,
		GiaNhap int not null,
		GiaBan int not null,
		NSX date not null,
		HSD date not null,
	)

alter table SanPham add constraint C_SP CHeck(nsx<hsd)
DELETE from SanPham where MaSP = ''

--KHACHHANG(MaKH, TenKH, CMT, DiaChi, SoDienThoai, Email) 
	create table KhachHang
	(
		MaKH varchar(10) primary key, 
		TenKH nvarchar(50) not null, 
		Ngaysinh date check (ngaysinh<getdate()) not null,
		DiaChi nvarchar(50) not null, 
		SoDienThoai varchar(12) unique, 
		Email varchar(50) unique
	)
	go
--HOADONXUAT(MaHD, MaKH, NgayLapHD, MaNV, PhuongThucTT) 
	create table HoaDonXuat
	(
		MaHD int identity primary key, 
		MaKH varchar(10) foreign key references KhachHang, 
		NgayLapHD date check (ngaylaphd>=getdate())not null, 
		MaNV varchar(10) foreign key references nhanvien, 
		PhuongThucTT nvarchar(50) not null
	)
	go
--CT_HOADON(MaHD, MaHang, SoLuongMua, DonGia)
	create table CT_HOADON
	(
		MaHD int identity foreign key references hoadonxuat, 
		MaSP varchar(10) foreign key references sanpham, 
		SoLuongMua int not null, 
		DonGia float not null
	)




