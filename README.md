# Inventory Shop Warehouse API

Aplikasi backend sederhana untuk manajemen warehouse shop menggunakan **Spring Boot** dengan database **PostgreSQL**.

---

## 🏃 Cara Setup & Menjalankan Aplikasi

### Requirement

* Docker / Docker Desktop
* Java 17 
* Maven 3+
* Spring Versi 3.2.5

### Running Menggunakan Docker

1. Build dan jalankan container:

```bash
docker-compose up --build
```

2. Base URL & Port Aplikasi, Port bisa di ubah sesuai kebutuhan:
   `http://localhost:9095`

3. Swagger UI:
   `http://localhost:9095/swagger-ui.html`

---

## 💾 Database

* PostgreSQL digunakan untuk penyimpanan data.
* Data otomatis persisten melalui volume Docker `db-data`.
* Struktur tabel utama:

    * `item` → menyimpan barang
    * `variant` → variasi barang (size, color)
    * `price` → harga tiap variant
    * `stock` → jumlah stok tiap variant
    * `history` → riwayat transaksi stok

---

## 🏗️ Desain Aplikasi

* **Spring Boot 3.2.2**: framework utama
* **JPA / Hibernate**: ORM untuk akses DB
* **DTO** digunakan untuk mapping beberapa Object dengan Rapi dan Konsisten
* **Global Exception Handler**: Untuk handle Global Struktur Response JSON

```json
{
  "status": true|false,
  "message": "pesan",
  "data": {...}
}
```

* **Transaction API** untuk mengurangi stok dengan reference otomatis
* **Docker + docker-compose**: mempermudah deployment & persistance data
* **Configs**: untuk beberapa configurasi aplikasi
* **Models**: untuk memetakan data yang ada di Database
* **Services**: untuk bisnis logic aplikasi
* **repository**: untuk mengakses yang berhubungan dengan data
---

## 📌 Goal

* Variant selalu terkait dengan satu Item
* Setiap Variant memiliki 1 Price & 1 Stock
* Stok tidak bisa negatif
* Hanya 1 warehouse, tidak ada multi-lokasi

## ⚡ API Endpoint
* Item

    * GET /api/items → list semua item beserta variant, harga, stok

    * POST /api/items → buat item + variant

    * PUT /api/items/{id} → update item
    
    * DELETE /api/items/{id} → hapus item + semua variant & stok terkait
 
* Transaction
    
    POST /api/transactions
```Json
{
  "variants": {
    "1": 2,
    "3": 1
  },
  "reference": "INV-12345"
} 
```
    * variants → id variant : qty
    
    * reference → opsional

## 📦 Build Lokal

```bash
./mvnw clean package
java -jar target/app-0.0.1-SNAPSHOT.jar
``` 

## 🔗 Swagger
Jika sudah berhasil running app nya silahkan buka swagger UI untuk melihat endpoint secara interaktif
dengan mangakses : 
```bash
http://localhost:9095/swagger-ui.html
```

## 👤 Author
Adi Siswoyo