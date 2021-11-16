create database Do_An_De_04
use Do_An_De_04

select * from SanPham
update sanpham set LoaiSP = N'Đồ uống' where TenSP like N'sữa %'

select * from LoaiSanPham
delete from loaiSanPham where loaisp=N'Dầu gội'
create table LoaiSanPham
(
	LoaiSP nvarchar(50)
)
insert into LoaiSanPham values (N'Thực phẩm khô')
select * from NhaSanXuat
insert into NhaSanXuat values (N'Pepsi'),(N'Coca'),(N'Vinamilk'),(N'Tường An'),(N'Thorakao'),(N'Pantene'),(N'Clear'),(N'X-Men')
CREATE TABLE NhaSanXuat
(
	NhaSX nvarchar(50) not null,
)

select distinct LoaiSP from SanPham

select * from DonDatHang order by MaHD desc
--select * from BanHang
--select * from KhachHang

select distinct maHD from MaDonHang
select distinct maHD from MaBanHang
delete from mabanhang where mahd = ''
delete from DonDatHang where MaHD = 'DH10'
select distinct maHD from DonDatHang order by MaHD

delete SanPham
sp_help SanPham

select MaHD,NgayDat,a.MaSP,a.MaKH,SoLuongMua,TongTien,TenSP,TenKH 
from DonDatHang a,KhachHang b,SanPham c 
where a.MaKH=b.MaKH and a.MaSP=c.MaSP and maHD = 'DH1' order by MaHD

create table MaDonHang
(
	MaHD varchar(10)
)
delete from MaBanHang
delete from MaDonHang
create table MaBanHang
(
	MaHD varchar(10)
)
select * from MaBanHang

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
select top 5 * from SanPham order by SoLuong desc
select * from SanPham where NhaSX = N'coCA'

select * from SanPham order by ABS(DATEDIFF(mm,NSX,GETDATE())) -- (NGÀY HIỆN TẠI - NGÀY SX)
select top 5 * from SanPham order by NSX desc
--select * from SanPham where NSX <= GETDATE()
select * from SanPham
alter table SanPham add constraint C_SP CHeck(nsx<hsd)
alter table SanPham ALTER COLUMN GiaNhap int
DELETE from SanPham where MaSP = ''
--BANHANG
drop table banhang
--create table BanHang
--(
--	MaHD varchar(10),
--	NgayDat datetime not null,
--	MaSP varchar(10) foreign key references sanpham,
--	SoLuongMua int not null,
--	TongTien int not null,
--	constraint PK_BH primary key(mahd,NgayDat)
--)

create table BanHang
(
	MaHD varchar(10),
	MaSP varchar(10) foreign key references sanpham,
	SoLuongMua int not null,
	TongTien int not null
)

insert into BanHang values ('HD3',GETDATE(),'DU2',12,20000000)

select * from BanHang
delete from BanHang
-- DOANH THU
drop table DoanhThu
create table DoanhThu
(
	MaHD varchar(10) primary key,
	NgayBan datetime not null,
	TongTienThu int,
	TienNhan int,
	TienDu int
)

select * from DoanhThu where NgayBan between '2021/11/11' and '2021/11/11'

 ALTER TABLE DoanhThu  ALTER COLUMN  NgayBan date
insert into DoanhThu(MaHD,NgayDat,MaSP,TenSP,SoLuongMua) values ()
select * from DoanhThu
drop table DoanhThu
delete from DoanhThu
--DONDATHANG
create table DonDatHang
(
	MaHD varchar(10) not null,
	NgayDat date check (ngaydat >= getdate()) not null,
	MaSP varchar(10) foreign key references sanpham,
	MaKH varchar(10) foreign key references khachhang,
	SoLuongMua int not null,
	TongTien int not null
)
drop table DonDatHang
select * from DonDatHang
delete from dondathang
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



