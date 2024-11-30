-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 30 Nov 2024 pada 13.18
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gudang_computer`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `id_barang` char(50) NOT NULL,
  `nama_barang` varchar(225) NOT NULL,
  `id_type` int(11) NOT NULL,
  `stok` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `tgl_masuk` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`id_barang`, `nama_barang`, `id_type`, `stok`, `harga`, `tgl_masuk`) VALUES
('asbsajsja', 'HAhahah', 1, 10, 12223, '2024-11-26T02:04:40.824764500Z'),
('Barang3', 'Ausss T099', 4, 10, 99999899, '2024-11-25T23:49:11.622508700Z'),
('fufufafa123', 'Barang am', 6, 10, 12000, '2024-11-26T01:45:42.595349Z'),
('hajshasash', 'HP123', 1, 10, 12333, '2024-11-25T05:31:10.652629300Z');

-- --------------------------------------------------------

--
-- Struktur dari tabel `login`
--

CREATE TABLE `login` (
  `email` varchar(20) NOT NULL,
  `password` varchar(200) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `login`
--

INSERT INTO `login` (`email`, `password`, `nama`) VALUES
('admin@gmail.com', '12345678', 'Ilham');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` char(50) NOT NULL,
  `id_barang` varchar(225) NOT NULL,
  `tgl_keluar` varchar(225) NOT NULL,
  `tujuan` text NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_barang`, `tgl_keluar`, `tujuan`, `stok`) VALUES
('Haa123', 'asbsajsja', '2024-11-26T02:04:10.809755100Z', 'Bumi', 12),
('Transit5', 'fufufafa123', '2024-11-26T05:40:01.403658900Z', 'Kandangan', 23);

-- --------------------------------------------------------

--
-- Struktur dari tabel `type`
--

CREATE TABLE `type` (
  `id_type` int(11) NOT NULL,
  `kategori` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `type`
--

INSERT INTO `type` (`id_type`, `kategori`) VALUES
(1, 'Computer'),
(2, 'Accesoris'),
(3, 'Laptop'),
(4, 'Makanan'),
(5, 'Minuman'),
(6, 'Tambahan'),
(7, 'HAHAHAHA'),
(8, 'GGZidan');

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `v_barangtype`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `v_barangtype` (
`IDBarang` char(50)
,`NamaBarang` varchar(225)
,`Kategori` varchar(225)
,`Stok` int(11)
,`Harga` int(11)
,`TglMasuk` varchar(225)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `v_transaksi_barang`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `v_transaksi_barang` (
`idTransit` char(50)
,`namaBarang` varchar(225)
,`Kategori` varchar(225)
,`tglTransit` varchar(225)
,`Tujuan` text
,`Stok` int(11)
);

-- --------------------------------------------------------

--
-- Struktur untuk view `v_barangtype`
--
DROP TABLE IF EXISTS `v_barangtype`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_barangtype`  AS SELECT `a`.`id_barang` AS `IDBarang`, `a`.`nama_barang` AS `NamaBarang`, `b`.`kategori` AS `Kategori`, `a`.`stok` AS `Stok`, `a`.`harga` AS `Harga`, `a`.`tgl_masuk` AS `TglMasuk` FROM (`barang` `a` join `type` `b`) WHERE `a`.`id_type` = `b`.`id_type` ;

-- --------------------------------------------------------

--
-- Struktur untuk view `v_transaksi_barang`
--
DROP TABLE IF EXISTS `v_transaksi_barang`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_transaksi_barang`  AS SELECT `a`.`id_transaksi` AS `idTransit`, `b`.`nama_barang` AS `namaBarang`, `c`.`kategori` AS `Kategori`, `a`.`tgl_keluar` AS `tglTransit`, `a`.`tujuan` AS `Tujuan`, `a`.`stok` AS `Stok` FROM ((`transaksi` `a` join `barang` `b`) join `type` `c` on(`a`.`id_barang` = `b`.`id_barang` and `b`.`id_type` = `c`.`id_type`)) ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`),
  ADD KEY `FK_type` (`id_type`);

--
-- Indeks untuk tabel `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`email`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Indeks untuk tabel `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`id_type`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `type`
--
ALTER TABLE `type`
  MODIFY `id_type` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `FK_type` FOREIGN KEY (`id_type`) REFERENCES `type` (`id_type`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
