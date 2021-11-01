create database Do_An_De_04
use Do_An_De_04
select * from SanPham
delete SanPham
sp_help SanPham


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
select * from SanPham

alter table SanPham add constraint C_SP CHeck(nsx<hsd)
alter table SanPham ALTER COLUMN GiaNhap int
DELETE from SanPham where MaSP = ''

--DONDATHANG
create table DonDatHang
(
	MaHD int identity primary key,
	NgayDat date not null,
	MaSP varchar(10) foreign key references sanpham,
	MaKH varchar(10) foreign key references khachhang,
	TongTien float not null,
)
select * from DonDatHang
delete from DonDatHang
alter table DonDatHang ALTER COLUMN TongTien int
sp_help dondathang
--KHACHHANG
select * from KhachHang
create table KhachHang
(
	MaKH varchar(10) primary key,
	TenKH nvarchar(50) not null,
	GioiTinh nvarchar(10) not null,
	DiaChi nvarchar(50) not null,
	SDT varchar(12) not null
)
select TenKH,SDT,DiaChi from KhachHang where MaKH = 'HN0'



