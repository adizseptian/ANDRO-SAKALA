package id.sakala.merchant.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.sakala.merchant.data.ProductEntity
import id.sakala.merchant.ui.theme.*
import id.sakala.merchant.domain.formatRupiah

private enum class Tab(val title: String, val symbol: String) {
    HOME("Beranda", "⌂"), STOCK("Stok", "▦"), ENTRY("Catat", "+"),
    DEPOSIT("Setoran", "◎"), MORE("Lainnya", "•••")
}

@Composable
fun SakalaApp(vm: AppViewModel) {
    var tab by remember { mutableStateOf(Tab.HOME) }
    var message by remember { mutableStateOf<String?>(null) }

    Scaffold(
        containerColor = Porcelain,
        snackbarHost = { SnackbarHost(remember { SnackbarHostState() }) },
        bottomBar = {
            NavigationBar(containerColor = SurfaceWhite) {
                Tab.entries.forEach { item ->
                    NavigationBarItem(
                        selected = tab == item,
                        onClick = { tab = item },
                        icon = { Text(item.symbol, fontSize = 19.sp) },
                        label = { Text(item.title, fontSize = 10.sp) }
                    )
                }
            }
        }
    ) { padding ->
        Column(
            Modifier.fillMaxSize().background(Porcelain).padding(padding)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(22.dp))
            Text("SAKALA ATELIER", color = Evergreen, fontSize = 11.sp,
                fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
            Text("Merchant Pro", color = Muted, fontSize = 13.sp)
            Spacer(Modifier.height(24.dp))
            when (tab) {
                Tab.HOME -> HomeScreen(vm.products.value, vm.events.value)
                Tab.STOCK -> StockScreen(vm.products.value) { name, unit, pack, price ->
                    vm.addProduct(name, unit, pack, price) { message = it ?: "Produk berhasil disimpan." }
                }
                Tab.ENTRY -> EntryScreen { amount, funding, note ->
                    vm.recordExpense(amount, funding, note) { message = it ?: "Pengeluaran berhasil dicatat." }
                }
                Tab.DEPOSIT -> DepositScreen { amount, note ->
                    vm.recordDeposit(amount, note) { message = it ?: "Setoran berhasil dicatat." }
                }
                Tab.MORE -> MoreScreen()
            }
            message?.let {
                Spacer(Modifier.height(8.dp))
                Text(it, color = if (it.contains("berhasil")) Success else Error, fontSize = 12.sp)
            }
        }
    }
}

@Composable
private fun ScreenTitle(title: String, subtitle: String) {
    Text(title, color = Ink, fontSize = 27.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(6.dp))
    Text(subtitle, color = Muted, fontSize = 13.sp)
    Spacer(Modifier.height(20.dp))
}

@Composable
private fun HomeScreen(products: List<ProductEntity>, events: List<id.sakala.merchant.data.LedgerEventEntity>) {
    ScreenTitle("Ringkasan toko", "Gambaran aktivitas usaha yang tercatat.")
    Surface(color = Evergreen, shape = RoundedCornerShape(18.dp), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(22.dp)) {
            Text("KONDISI USAHA", color = Color(0xFFD6E5DC), fontSize = 11.sp)
            Spacer(Modifier.height(12.dp))
            Text("${products.size} produk terdaftar", color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.SemiBold)
            Text("${events.size} aktivitas tersimpan", color = Color(0xFFD6E5DC), fontSize = 13.sp)
        }
    }
    Spacer(Modifier.height(14.dp))
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        MetricCard("Produk", products.size.toString(), Modifier.weight(1f))
        MetricCard("Aktivitas", events.size.toString(), Modifier.weight(1f))
    }
    Spacer(Modifier.height(18.dp))
    Text("Prinsip pencatatan", color = Ink, fontWeight = FontWeight.SemiBold)
    Spacer(Modifier.height(6.dp))
    Text("Sistem menyimpan kejadian usaha. Nilai yang belum diketahui tidak dianggap nol.", color = Muted, fontSize = 13.sp)
}

@Composable
private fun MetricCard(label: String, value: String, modifier: Modifier = Modifier) {
    Surface(modifier, color = SurfaceWhite, shape = RoundedCornerShape(16.dp), border = BorderStroke(1.dp, Line)) {
        Column(Modifier.padding(16.dp)) {
            Text(label, color = Muted, fontSize = 12.sp)
            Spacer(Modifier.height(8.dp))
            Text(value, color = Ink, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun StockScreen(products: List<ProductEntity>, addProduct: (String, String, String, String) -> Unit) {
    var showForm by remember { mutableStateOf(false) }
    ScreenTitle("Persediaan barang", "Kelola produk dan unit jual.")
    Button(onClick = { showForm = true }, modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp)) { Text("Tambah produk") }
    Spacer(Modifier.height(12.dp))
    if (products.isEmpty()) {
        EmptyState("Belum ada produk", "Tambahkan produk untuk mulai mencatat persediaan.")
    } else {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(products, key = { it.id }) { product ->
                Surface(color = SurfaceWhite, shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, Line), modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp)) {
                        Text(product.name, fontWeight = FontWeight.SemiBold)
                        Text("Satuan: ${product.baseUnit} • Isi kemasan: ${product.unitsPerPackage}", color = Muted, fontSize = 12.sp)
                        Text("Stok diketahui: ${product.knownUnits} • Modal belum diketahui: ${product.unknownUnits}", color = Muted, fontSize = 12.sp)
                        Text("Harga jual: ${formatRupiah(product.sellingPrice)}", color = Evergreen, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    }
    if (showForm) ProductDialog(onDismiss = { showForm = false }) { n, u, p, price ->
        addProduct(n, u, p, price); showForm = false
    }
}

@Composable
private fun ProductDialog(onDismiss: () -> Unit, onSave: (String, String, String, String) -> Unit) {
    var name by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("pcs") }
    var pack by remember { mutableStateOf("1") }
    var price by remember { mutableStateOf("0") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Tambah produk") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(name, { name = it }, label = { Text("Nama produk") }, singleLine = true)
                OutlinedTextField(unit, { unit = it }, label = { Text("Satuan dasar") }, singleLine = true)
                OutlinedTextField(pack, { pack = it }, label = { Text("Isi per kemasan") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), singleLine = true)
                OutlinedTextField(price, { price = it }, label = { Text("Harga jual (Rupiah)") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), singleLine = true)
            }
        },
        confirmButton = { TextButton(onClick = { onSave(name, unit, pack, price) }, enabled = name.isNotBlank() && (pack.toLongOrNull() ?: 0) > 0 && (price.toLongOrNull() ?: -1) >= 0) { Text("Simpan") } },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Batal") } }
    )
}

@Composable
private fun EntryScreen(record: (String, String, String) -> Unit) {
    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var funding by remember { mutableStateOf("UANG_TOKO") }
    ScreenTitle("Catat aktivitas", "Pencatatan pengeluaran operasional.")
    OutlinedTextField(amount, { amount = it }, label = { Text("Jumlah pengeluaran (Rupiah)") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
    Spacer(Modifier.height(10.dp))
    Text("Sumber uang", fontWeight = FontWeight.SemiBold)
    listOf("UANG_TOKO" to "Uang Toko", "UANG_PRIBADI" to "Uang Pribadi", "BELUM_TAHU" to "Belum Tahu").forEach { (value, label) ->
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            RadioButton(selected = funding == value, onClick = { funding = value })
            Text(label, color = Ink)
        }
    }
    OutlinedTextField(note, { note = it }, label = { Text("Keterangan") }, modifier = Modifier.fillMaxWidth())
    Spacer(Modifier.height(14.dp))
    Button(onClick = { record(amount, funding, note); amount = ""; note = "" },
        enabled = (amount.toLongOrNull() ?: 0) > 0, modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp)) { Text("Simpan pengeluaran") }
}

@Composable
private fun DepositScreen(record: (String, String) -> Unit) {
    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    ScreenTitle("Setoran", "Catat uang tunai yang benar-benar diserahkan.")
    OutlinedTextField(amount, { amount = it }, label = { Text("Jumlah uang diserahkan (Rupiah)") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
    Spacer(Modifier.height(10.dp))
    OutlinedTextField(note, { note = it }, label = { Text("Keterangan (opsional)") }, modifier = Modifier.fillMaxWidth())
    Spacer(Modifier.height(14.dp))
    Button(onClick = { record(amount, note); amount = ""; note = "" },
        enabled = (amount.toLongOrNull() ?: 0) > 0, modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp)) { Text("Catat penyerahan") }
    Spacer(Modifier.height(12.dp))
    Text("Setoran bukan omzet atau keuntungan. Setoran adalah penyerahan uang fisik.", color = Muted, fontSize = 12.sp)
}

@Composable
private fun MoreScreen() {
    ScreenTitle("Lainnya", "Informasi dan pengelolaan aplikasi.")
    listOf(
        "Data tersimpan lokal di perangkat",
        "Tidak menggunakan login atau sinkronisasi cloud",
        "Backup dan restore terenkripsi — tahap implementasi berikutnya",
        "Periode usaha Senin–Minggu (WIB)"
    ).forEach {
        Surface(color = SurfaceWhite, shape = RoundedCornerShape(14.dp),
            border = BorderStroke(1.dp, Line), modifier = Modifier.fillMaxWidth()) {
            Text(it, Modifier.padding(16.dp), color = Ink, fontSize = 13.sp)
        }
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
private fun EmptyState(title: String, description: String) {
    Surface(color = SurfaceWhite, shape = RoundedCornerShape(16.dp), border = BorderStroke(1.dp, Line)) {
        Column(Modifier.padding(20.dp)) {
            Text(title, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(6.dp))
            Text(description, color = Muted, fontSize = 13.sp)
        }
    }
}
