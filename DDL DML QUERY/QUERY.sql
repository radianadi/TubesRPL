/* query 1 */

select 'penumpang dengan nama  '||TRANSLATE(p.nama_penumpang,'aiueo','41@#0') ||' dengan id tiket  '||t.id_tiket
||' melakukan perjalanan dari stasiun '||s.nama_stasiun ||' di kota '||k.nama_kota ||' menuju stasiun '||
j.stasiun_tujuan||' menggunakan kereta '||ke.nama_kereta||'berangkat pada pukul '||j.jam_berangkat
from penumpang p,tiket t,kota k,jadwal j,penjadwalan pj,kereta ke,keretastasiun ks,stasiun s
where p.id_penumpang=t.id_penumpang and t.id_jadwal=j.id_jadwal and pj.id_jadwal=j.id_jadwal and
pj.id_kereta=ke.id_kereta and ks.id_kereta=ke.id_kereta and ks.id_stasiun=s.id_stasiun and s.id_kota=k.id_kota
and t.id_tiket='1342303';

/* query 2 */

select pet.id_petugas as ID_PETUGAS,UPPER(pet.nama_petugas) as NAMA_PETUGAS,count(t.id_tiket)AS JUMLAH_TIKET
from petugas pet,tiket t
where t.id_petugas=pet.id_petugas
group by pet.id_petugas,pet.nama_petugas
;
/* query 3 */

select sysdate as tanggal,j.id_jadwal as jadwal,ke.nama_kereta as kereta,s.nama_stasiun as "stasiun awal",
j.stasiun_tujuan as "stasiun tujuan",count(t.id_tiket) as "jumlah penumpang"
from tiket t,jadwal j,kereta ke,penjadwalan pj,stasiun s,keretastasiun ks
where t.id_jadwal=j.id_jadwal and pj.id_kereta=ke.id_kereta and pj.id_jadwal=j.id_jadwal and ks.id_kereta=ke.id_kereta and ks.id_stasiun=s.id_stasiun
group by j.id_jadwal,ke.nama_kereta ,j.stasiun_tujuan,s.nama_stasiun;


/* subquery 1 */

declare
nker kereta.nama_kereta%type;
jker jenis.nama_jenis%type;
harga kereta.harga%type;
ngerbong kereta.jumlah_gerbong%type;
nkursi kereta.jumlah_kursi%type;
sts1 rute.stasiun_1%type;
sts2 rute.stasiun_2%type;
sts3 rute.stasiun_3%type;
begin
select k.nama_kereta ,je.nama_jenis ,k.harga,k.jumlah_gerbong,k.jumlah_kursi ,r.stasiun_1,r.stasiun_2,r.stasiun_3 into nker,jker,harga,ngerbong,nkursi,sts1,sts2,sts3 
from kereta k,jenis je,rute r
where harga=(select max(harga) from kereta) and k.id_jenis=je.id_jenis and k.id_rute=r.id_rute;
dbms_output.put_line('====================================================');
dbms_output.put_line('=======Kereta Dengan Harga Tiket termahal===========');
dbms_output.put_line('====================================================');
dbms_output.put_line('Nama Kereta   :'||nker);
dbms_output.put_line('Jenis Kereta  :'||jker);
dbms_output.put_line('Harga Tiket   :'||harga);
dbms_output.put_line('Jumlah gerbong:'||ngerbong);
dbms_output.put_line('Jumlah kursi  :'||nkursi);
dbms_output.put_line('Rute          :'||sts1||'-'||sts2||'-'||sts3);
end;
/





/* subquery 2 */
declare
cursor cur is 
select nama_stasiun ns from stasiun s
where s.id_kota in
(select  id_kota 
from kota
where nama_kota='BANDUNG');
i number :=1;
begin
dbms_output.put_line('====================================================');
dbms_output.put_line('Daftar Stasiun di Kota Bandung');
dbms_output.put_line('====================================================');
for x in cur 
loop 
     dbms_output.put_line(i||' .'||x.ns);
	 i:=i+1;
end loop;
end;
/

/* package 1 */
create or replace package body P1 is
procedure petterajin is
   v_id char(5);
   v_max number:=0;
   v_nama varchar2(30);
   cursor cur is
   select pet.id_petugas as idp,pet.nama_petugas as nmp,count(t.id_tiket)AS jtik
   from petugas pet,tiket t
   where t.id_petugas=pet.id_petugas
   group by pet.id_petugas,pet.nama_petugas;
begin
   for x in cur
      loop
	    dbms_output.put_line('petugas dengan nama '||x.nmp||' dengan id '||x.idp||' Telah Bertugas dan melayani '||x.jtik||' tiket');
	  end loop;
	  	for x in cur
		loop
		   if(v_max<x.jtik) then
		   v_id:=x.idp;
		   v_max:=x.jtik;
		   v_nama:=x.nmp;
		   end if;
		end loop;
		dbms_output.put_line(' ');
	    dbms_output.put_line('===============================================================');
		dbms_output.put_line('===============PETUGAS TERBANYAK MELAYANI TIKET================');
		dbms_output.put_line('ID PETUGAS        :'||v_id);
		dbms_output.put_line('NAMA PETUGAS      :'||v_nama);
		dbms_output.put_line('JUMLAH TIKET      :'||v_max);
		 dbms_output.put_line('===============================================================');
	
		
end;

procedure terbanyak  is
   v_idj char(9);
   v_nker varchar2(25);
   v_namas varchar2(25);
   v_stuj varchar2(20);
   v_max number:=0;
   cursor cur is
   select j.id_jadwal as idj,ke.nama_kereta as nmker,s.nama_stasiun as stawal,j.stasiun_tujuan as stuj,count(t.id_tiket) as jpen
   from tiket t,jadwal j,kereta ke,penjadwalan pj,stasiun s,keretastasiun ks
   where t.id_jadwal=j.id_jadwal and pj.id_kereta=ke.id_kereta and pj.id_jadwal=j.id_jadwal and ks.id_kereta=ke.id_kereta and ks.id_stasiun=s.id_stasiun
   group by j.id_jadwal,ke.nama_kereta ,j.stasiun_tujuan,s.nama_stasiun;

begin
   dbms_output.put_line('Rekapan Penumpang Kereta Api');
   for x in cur
      loop
	    dbms_output.put_line('ID Jadwal '||x.idj||' dengan kereta '||x.nmker||' dengan jumlah '||x.jpen||'penumpang ');
		
	  end loop;
	  	for x in cur
		loop
		   if(v_max<x.jpen) then
		   v_idj:=x.idj;
		   v_nker:=x.nmker;
		   v_namas:=x.stawal;
		   v_stuj:=x.stuj;
		   v_max:=x.jpen;
		   
		   end if;
		end loop;
		dbms_output.put_line(' ');
	    dbms_output.put_line('===============================================================');
		dbms_output.put_line('===============KERETA DENGAN PENUMPANG TERBANYAK===============');
		dbms_output.put_line('ID JADWAL        :'||v_idj);
		dbms_output.put_line('NAMA KERETA      :'||v_nker);
		dbms_output.put_line('STASIUN AWAL     :'||v_namas);
		dbms_output.put_line('STASIUN TUJUAN   :'||v_stuj);
		dbms_output.put_line('JUMLAH PENUMPANG :'||v_max);
		dbms_output.put_line('===============================================================');
	
end;
end p1;
/
execute p1.petterajin;
execute p1.terbanyak;


/* package 2 */
create or replace package P2 is
procedure caridatapetugas(idpet in petugas.nama_petugas%type);
procedure datakereta(idker in kereta.id_kereta%type);
end P2;
/

create or replace package body p2 is
procedure caridatapetugas(idpet in petugas.nama_petugas%type) is
v_nama varchar2(25);
v_umur number(2);
v_jk   varchar2(10);
v_almt varchar2(38);
begin
select p.nama_petugas,p.jenis_kelamin,p.umur,p.alamat
into v_nama,v_jk,v_umur,v_almt
from petugas p
where p.id_petugas=idpet;
dbms_output.put_line('=============================================');
dbms_output.put_line('data petugas dengan id petugas:'||idpet);
dbms_output.put_line('=============================================');
dbms_output.put_line('id petugas     :'||idpet);
dbms_output.put_line('nama petugas   :'||v_nama);
dbms_output.put_line('jenis kelamin  :'||v_jk);
dbms_output.put_line('umur           :'||v_umur);
dbms_output.put_line('alamat         :'||v_almt);
exception 
when no_data_found then
dbms_output.put_line('tidak ada petugas dengan id tiket '||idpet);
end;

procedure datakereta(idker in kereta.id_kereta%type)is
v_nker varchar(25);
v_jkur number(3);
v_jger number(2);
v_stawal varchar(15);
v_stujuan varchar(15);
v_harga number(6);
begin
select k.nama_kereta,k.jumlah_kursi,k.jumlah_gerbong,k.harga
into v_nker,v_jkur,v_jger,v_harga
from kereta k
where k.id_kereta=idker;
dbms_output.put_line('=============================================');
dbms_output.put_line('data kereta dengan id kereta:'||idker);
dbms_output.put_line('=============================================');
dbms_output.put_line('id kereta       :'||idker);
dbms_output.put_line('nama kereta     :'||v_nker);
dbms_output.put_line('jumlah gerbong  :'||v_jkur);
dbms_output.put_line('jumlah kursi    :'||v_jger);
dbms_output.put_line('harga           :'||v_harga);
exception 
when no_data_found then
dbms_output.put_line('tidak ada kereta dengan id kereta '||idker);
end;

end p2;
/

execute p2.caridatapetugas('PT111');
execute p2.caridatapetugas('PT000');
execute p2.datakereta('AGW01');
execute p2.datakereta('AGW02');


/* function 1*/

create or replace function carialamatpenumpang(idj in jadwal.id_jadwal%type,idker in kereta.id_kereta%type,ger in tiket.gerbong%type,kur in tiket.nokursi%type)
return penumpang.alamat%type is
v_alamat penumpang.alamat%type;
begin 
select p.alamat into v_alamat
from penumpang p,tiket t,jadwal j,penjadwalan pj,kereta k
where p.id_penumpang=t.id_penumpang and t.id_jadwal=j.id_jadwal and pj.id_kereta=k.id_kereta and pj.id_jadwal=j.id_jadwal and pj.id_kereta=idker and pj.id_jadwal=idj and t.gerbong=ger and t.nokursi=kur;
return v_alamat;
exception
when no_data_found then
dbms_output.put_line('data tidak ditemukan');
end;
/


/* 1.implementasi */
declare 
in_id_jadwal jadwal.id_jadwal%type:='&idjadwal';
in_id_kereta kereta.id_kereta%type:='&idkereta';
in_ger tiket.gerbong%type:='&gerbong';
in_nokur tiket.nokursi%type:='&nokursi';
begin 
dbms_output.put_line('========================================');
dbms_output.put_line('data penumpang dengan:');
dbms_output.put_line('========================================');
dbms_output.put_line('id_jadwal:'||in_id_jadwal);
dbms_output.put_line('id_kereta:'||in_id_kereta);
dbms_output.put_line('gerbong  :'||in_ger);
dbms_output.put_line('nokursi  :'||in_nokur);
dbms_output.put_line('========================================');

dbms_output.put_line('alamat :'||carialamatpenumpang(in_id_jadwal,in_id_kereta,in_ger,in_nokur));
end;
/


/* function 2*/


create or replace function lihatdescjadwal(idj in jadwal.id_jadwal%type,stsawal out stasiun.nama_stasiun%type, jdat out jadwal.jam_datang%type,jber out jadwal.jam_berangkat%type,nker out kereta.nama_kereta%type,jker out jenis.nama_jenis%type,hartik out kereta.harga%type,rute1 out rute.stasiun_1%type,rute2 out rute.stasiun_2%type,rute3 out rute.stasiun_3%type)
return jadwal.stasiun_tujuan%type is
v_stasiuntuj jadwal.stasiun_tujuan%type;

begin
select j.stasiun_tujuan,s.nama_stasiun,j.jam_datang,j.jam_berangkat,k.nama_kereta,je.nama_jenis,k.harga,
r.stasiun_1,r.stasiun_2,r.stasiun_3 into v_stasiuntuj,stsawal,jdat,jber,nker,jker,hartik,rute1,rute2,rute3
from jadwal j,penjadwalan pj,kereta k,jenis je,rute r,stasiun s,keretastasiun ks
where j.id_jadwal=idj and pj.id_jadwal=j.id_jadwal and pj.id_kereta=k.id_kereta and k.id_jenis=je.id_jenis 
and r.id_rute=k.id_rute and ks.id_kereta=k.id_kereta and ks.id_stasiun=s.id_stasiun;
return v_stasiuntuj;
end;
/ 
/* 2.implementasi */
declare
idjad jadwal.id_jadwal%type:='&input_id_jadwal';
jamdat jadwal.jam_datang%type;
jamber jadwal.jam_berangkat%type; 
naker  kereta.nama_kereta%type;
jeker jenis.nama_jenis%type;
htiket kereta.harga%type;
krute1 rute.stasiun_1%type;
krute2 rute.stasiun_2%type;
krute3 rute.stasiun_3%type;
stawal stasiun.nama_stasiun%type;
begin
dbms_output.put_line('====================================================');
dbms_output.put_line('ID_JADWAL:'||idjad);
dbms_output.put_line('====================================================');
dbms_output.put_line('stasiun tujuan       :'||lihatdescjadwal(idjad,stawal,jamdat,jamber,naker,jeker,htiket,krute1,krute2,krute3));
dbms_output.put_line('Stasiun Keberangkatan:'||stawal);
dbms_output.put_line('nama kereta          :'||naker);
dbms_output.put_line('jenis kereta         :'||jeker);
dbms_output.put_line('jam datang           :'||jamdat);
dbms_output.put_line('jam berangkat        :'||jamber);
dbms_output.put_line('harga tiket          :Rp.'||htiket);
dbms_output.put_line('rute                 :'||krute1||'-'||krute2||'-'||krute3);
dbms_output.put_line('====================================================');
end;
/

/* procedure 1*/

create or replace procedure jum_tiket (tgl in tiket.tgl_pemesanan%type)is
jum number;
begin
select count(id_tiket) into jum
from tiket 
where tiket.tgl_pemesanan=tgl;
dbms_output.put_line('---------------------------------------------------');
dbms_output.put_line('total tiket pada tanggal '||tgl||':'||jum);
dbms_output.put_line('---------------------------------------------------');
end;
/


execute jum_tiket('15-01-2013');

/* procedure 2 */
create or replace procedure data_penumpang(idtiket in tiket.id_tiket%type)is
id penumpang.id_penumpang%type;
nama penumpang.nama_penumpang%type;
jk penumpang.jenis_kelamin%type;
umur penumpang.umur%type;
almt penumpang.alamat%type;
begin
select p.id_penumpang,p.nama_penumpang,p.jenis_kelamin,p.umur,p.alamat
into id,nama,jk,umur,almt
from penumpang p,tiket t
where t.id_tiket=idtiket and p.id_penumpang =t.id_penumpang;
dbms_output.put_line('=============================================');
dbms_output.put_line('data pengumpang dengan id tiket:'||idtiket);
dbms_output.put_line('=============================================');
dbms_output.put_line('id penummpang  :'||id);
dbms_output.put_line('nama penumpang :'||nama);
dbms_output.put_line('jenis kelamin  :'||jk);
dbms_output.put_line('umur           :'||umur);
dbms_output.put_line('alamat         :'||almt);
exception 
when no_data_found then
dbms_output.put_line('tidak ada pelanggan dengan id tiket '||idtiket);
end;
/

execute data_penumpang('1342303');

/* cursor 1 */

declare 
	cursor cur is
	select p.nama_penumpang as nm,t.tgl_pemesanan as tp,s.nama_stasiun as ns,j.stasiun_tujuan as st
	from penumpang p,tiket t,stasiun s,jadwal j,penjadwalan pj,kereta k,keretastasiun ks
	where p.id_penumpang=t.id_penumpang and j.id_jadwal=t.id_jadwal and pj.id_jadwal=j.id_jadwal and pj.id_kereta=k.id_kereta
	and ks.id_stasiun=s.id_stasiun and ks.id_kereta=k.id_kereta and nama_stasiun='St.Cimahi';
	begin
	      
	for x in cur
	loop
	
	      dbms_output.put_line(x.nm||' memesan tiket pada tanggal '||x.tp||' untuk keberangkatan dari stasiun '||x.ns||' menuju stasiun '||x.st);
	
	end loop;
end;
/
/* cursor 2 */
declare
cursor cur is
select pet.nama_petugas as np,count(ti.id_tiket) as jum
from petugas pet,tiket ti
where ti.id_petugas=pet.id_petugas
group by pet.nama_petugas;
begin
dbms_output.put_line('========================================');
dbms_output.put_line('====daftar penugasan pemesanan tiket====');
dbms_output.put_line('========================================');
for x in cur
loop 
dbms_output.put_line(x.np||' menangani '||x.jum||' pemesanan tiket');
end loop;
dbms_output.put_line('========================================');
end;
/


/* trigger 1 */



CREATE OR REPLACE TRIGGER aft_ins_tiket
AFTER INSERT ON tiket
FOR EACH ROW
DECLARE temp_jml NUMBER;
tempid kereta.id_kereta%type;
BEGIN
    SELECT k.jumlah_kursi,k.id_kereta
	into temp_jml,tempid
    from kereta k,jadwal j, penjadwalan pj
    where pj.id_penjadwalan=:new.id_penjadwalan and j.id_jadwal=pj.id_jadwal and pj.id_kereta=k.id_kereta;
    if inserting then
	temp_jml:=temp_jml-1;
	update kereta
	set jumlah_kursi=temp_jml
	where id_kereta=tempid;
	dbms_output.put_line('-----Terimakasih,Pemesanan tiket berhasil----');
	dbms_output.put_line('             ID_KERETA:'||tempid);
	dbms_output.put_line('             SISA TIKET--:'||temp_jml);
	dbms_output.put_line('----------------------------------------------');
    end if;
end;
/


