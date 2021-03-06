/*
**
** The C code is generated by ATS/Postiats
** The compilation time is: 2013-7-15: 20h:10m
**
*/

/*
** include runtime header files
*/
#ifndef _ATS_CCOMP_HEADER_NONE
#include "pats_ccomp_config.h"
#include "pats_ccomp_basics.h"
#include "pats_ccomp_typedefs.h"
#include "pats_ccomp_instrset.h"
#include "pats_ccomp_memalloc.h"
#include "pats_ccomp_memalloca.h"
#include "pats_ccomp_exception.h"
#endif /* _ATS_CCOMP_HEADER_NONE */


/*
** include prelude cats files
*/
#ifndef _ATS_CCOMP_PRELUDE_NONE
//
#include "prelude/CATS/basics.cats"
#include "prelude/CATS/integer.cats"
#include "prelude/CATS/memory.cats"
#include "prelude/CATS/pointer.cats"
#include "prelude/CATS/bool.cats"
#include "prelude/CATS/char.cats"
#include "prelude/CATS/float.cats"
#include "prelude/CATS/string.cats"
#include "prelude/CATS/strptr.cats"
//
#include "prelude/CATS/filebas.cats"
//
#include "prelude/CATS/list.cats"
#include "prelude/CATS/option.cats"
#include "prelude/CATS/array.cats"
#include "prelude/CATS/arrayptr.cats"
#include "prelude/CATS/arrayref.cats"
#include "prelude/CATS/matrix.cats"
#include "prelude/CATS/matrixptr.cats"
//
#endif /* _ATS_CCOMP_PRELUDE_NONE */

/*
staload-prologues(beg)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 1636(line=51, offs=1) -- 1675(line=51, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 26347(line=693, offs=1) -- 26378(line=693, offs=32)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/SATS/stdlib.sats: 1379(line=35, offs=1) -- 1418(line=37, offs=3)
*/

#include "libc/CATS/stdlib.cats"
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/SATS/stdlib.sats: 1814(line=54, offs=1) -- 1854(line=55, offs=33)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/string.dats: 1609(line=48, offs=1) -- 1648(line=48, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/strptr.dats: 1609(line=48, offs=1) -- 1648(line=48, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/strptr.dats: 1671(line=52, offs=1) -- 1718(line=52, offs=48)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 1636(line=51, offs=1) -- 1675(line=51, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 26347(line=693, offs=1) -- 26378(line=693, offs=32)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/SATS/stdlib.sats: 1379(line=35, offs=1) -- 1418(line=37, offs=3)
*/

#include "libc/CATS/stdlib.cats"
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/SATS/stdlib.sats: 1814(line=54, offs=1) -- 1854(line=55, offs=33)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/pointer.dats: 1533(line=44, offs=1) -- 1572(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/filebas.dats: 1613(line=48, offs=1) -- 1652(line=48, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/filebas.dats: 1675(line=52, offs=1) -- 1722(line=52, offs=48)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 1636(line=51, offs=1) -- 1675(line=51, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 26347(line=693, offs=1) -- 26378(line=693, offs=32)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/SATS/stdlib.sats: 1379(line=35, offs=1) -- 1418(line=37, offs=3)
*/

#include "libc/CATS/stdlib.cats"
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/SATS/stdlib.sats: 1814(line=54, offs=1) -- 1854(line=55, offs=33)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/filebas.dats: 1745(line=56, offs=1) -- 1783(line=56, offs=39)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/SATS/stdio.sats: 1380(line=35, offs=1) -- 1418(line=37, offs=3)
*/

#include "libc/CATS/stdio.cats"
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/SATS/stdio.sats: 1855(line=57, offs=1) -- 1897(line=59, offs=27)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/libc/sys/SATS/types.sats: 1380(line=35, offs=1) -- 1422(line=37, offs=3)
*/

#include "libc/sys/CATS/types.cats"
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/list.dats: 1533(line=44, offs=1) -- 1572(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/list.dats: 1573(line=45, offs=1) -- 1619(line=45, offs=47)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/unsafe.dats: 1532(line=44, offs=1) -- 1566(line=44, offs=35)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/list_vt.dats: 1536(line=44, offs=1) -- 1575(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/list_vt_mergesort.dats: 1546(line=44, offs=1) -- 1585(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/list_vt_quicksort.dats: 1546(line=44, offs=1) -- 1585(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/array.dats: 1534(line=44, offs=1) -- 1573(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/array.dats: 1574(line=45, offs=1) -- 1616(line=45, offs=43)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/array_bsearch.dats: 1531(line=44, offs=1) -- 1570(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/array_quicksort.dats: 1531(line=44, offs=1) -- 1570(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/arrayptr.dats: 1532(line=44, offs=1) -- 1571(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/arrayref.dats: 1532(line=44, offs=1) -- 1571(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/matrix.dats: 1535(line=44, offs=1) -- 1574(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/matrixptr.dats: 1538(line=44, offs=1) -- 1577(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/matrixref.dats: 1538(line=44, offs=1) -- 1577(line=44, offs=40)
*/
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/unsafe.dats: 1532(line=44, offs=1) -- 1566(line=44, offs=35)
*/
/*
staload-prologues(end)
*/
/*
typedefs-for-tyrecs-and-tysums(beg)
*/
/*
typedefs-for-tyrecs-and-tysums(end)
*/
/*
dynconlst-declaration(beg)
*/
/*
dynconlst-declaration(end)
*/
/*
dyncstlst-declaration(beg)
*/
ATSdyncst_mac(atspre_arrayptr_make_arrpsz) ;
ATSdyncst_mac(atspre_add_ptr0_bsz) ;
ATSdyncst_mac(atspre_assert_errmsg_bool0) ;
ATSdyncst_mac(atspre_arrayptr_free) ;
/*
dyncstlst-declaration(end)
*/
/*
dynvalist-implementation(beg)
*/
/*
dynvalist-implementation(end)
*/
/*
exnconlst-declaration(beg)
*/
extern void the_atsexncon_initize (atstype_exncon *d2c, char *exnmsg) ;
/*
exnconlst-declaration(end)
*/
/*
assumelst-declaration(beg)
*/
/*
assumelst-declaration(end)
*/
ATSstaticdec()
atsvoid_t0ype
foo_0() ;

#if(0)
#if(0)
ATSglobaldec()
atstyvar_type(a)
ATSLIB_056$prelude_arrayptr_get_at_gint$1(atsrefarg0_type(atstkind_type(atstype_ptrk)), atstkind_t0ype(atstyvar_type(tk))) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_arrayptr_get_at_gint$1$1(atsrefarg0_type(atstkind_type(atstype_ptrk)), atstkind_t0ype(atstype_int)) ;

#if(0)
#if(0)
ATSglobaldec()
atstkind_type(atstype_ptrk)
ATSLIB_056$prelude_ptr1_add_gint$3(atstkind_type(atstype_ptrk), atstkind_t0ype(atstyvar_type(tk))) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_type(atstype_ptrk)
ATSLIB_056$prelude_ptr1_add_gint$3$1(atstkind_type(atstype_ptrk), atstkind_t0ype(atstype_int)) ;

#if(0)
#if(0)
ATSglobaldec()
atstkind_type(atstype_ptrk)
ATSLIB_056$prelude_ptr0_add_gint$5(atstkind_type(atstype_ptrk), atstkind_t0ype(atstyvar_type(tk))) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_type(atstype_ptrk)
ATSLIB_056$prelude_ptr0_add_gint$5$1(atstkind_type(atstype_ptrk), atstkind_t0ype(atstype_int)) ;

#if(0)
#if(0)
ATSglobaldec()
atstyvar_type(a)
ATSLIB_056$prelude_056$unsafe_ptr0_get$9(atstkind_type(atstype_ptrk)) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_056$unsafe_ptr0_get$9$1(atstkind_type(atstype_ptrk)) ;

#if(0)
#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g0int_int$11(atstkind_t0ype(atstyvar_type(tk)), atstkind_t0ype(atstype_int)) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g0int_int$11$1(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

#if(0)
ATSglobaldec()
atsvoid_t0ype
mainats_void_0() ;
#endif // end of [QUALIFIED]

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test23_array.dats: 51(line=5, offs=5) -- 220(line=14, offs=2)
*/
/*
local: 
global: foo_0$0(level=0)
local: 
global: 
*/
ATSstaticdec()
atsvoid_t0ype
foo_0()
{
/* tmpvardeclst(beg) */
ATStmpdec_void(tmpret0, atsvoid_t0ype) ;
ATStmpdec(tmp1, atstkind_type(atstype_ptrk)) ;
ATStmpdec(tmp2, atstype_arrpsz) ;
ATStmpdec(tmp3, atstype_arrptr) ;
ATStmpdec(tmp4, atstkind_t0ype(atstype_int)) ;
ATStmpdec_void(tmp23, atsvoid_t0ype) ;
ATStmpdec(tmp24, atstkind_t0ype(atstype_bool)) ;
ATStmpdec_void(tmp29, atsvoid_t0ype) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_foo_0:
/*
letpush(beg)
*/
/*
ATSINStmpdec(tmp2)) ;
*/
ATSINSstore_arrpsz_asz(tmp2, 3) ;
ATSINSstore_arrpsz_ptr(tmp2, atstkind_t0ype(atstype_int), 3) ;
ATSINSmove_arrpsz_ptr(tmp3, tmp2) ;
ATSINSpmove(tmp3, atstkind_t0ype(atstype_int), ATSPMVi0nt(3)) ;
ATSINSupdate_ptrinc(tmp3, atstkind_t0ype(atstype_int)) ;
ATSINSpmove(tmp3, atstkind_t0ype(atstype_int), ATSPMVi0nt(4)) ;
ATSINSupdate_ptrinc(tmp3, atstkind_t0ype(atstype_int)) ;
ATSINSpmove(tmp3, atstkind_t0ype(atstype_int), ATSPMVi0nt(5)) ;
ATSINSmove(tmp1, atspre_arrayptr_make_arrpsz(tmp2)) ;
/* (*nothing*) */
ATSINSmove(tmp4, ATSLIB_056$prelude_arrayptr_get_at_gint$1$1(ATSPMVrefarg0(tmp1), ATSPMVi0nt(0))) ;
ATSINSmove(tmp24, ATSLIB_056$prelude_eq_g0int_int$11$1(tmp4, ATSPMVi0nt(3))) ;
ATSINSmove_void(tmp23, atspre_assert_errmsg_bool0(tmp24, ATSCSTSPmyloc("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test23_array.dats: 174(line=12, offs=10) -- 190(line=12, offs=26)"))) ;
ATSINSmove_void(tmp29, atspre_arrayptr_free(tmp1)) ;
/*
letpush(end)
*/

ATSINSmove_void(tmpret0, ATSempty()) ;
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn_void(tmpret0) ;
} /* end of [foo_0] */

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/arrayptr.dats: 4515(line=219, offs=3) -- 4604(line=222, offs=4)
*/
/*
local: 
global: arrayptr_get_at_gint$1$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = a(5249), tk(5250)
tmparg = S2Evar(a(5249)); S2Evar(tk(5250))
tmpsub = None()
*/
atstyvar_type(a)
ATSLIB_056$prelude_arrayptr_get_at_gint$1(atsrefarg0_type(atstkind_type(atstype_ptrk)) arg0, atstkind_t0ype(atstyvar_type(tk)) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret5, atstyvar_type(a)) ;
ATStmpdec(tmp6, atstkind_type(atstype_ptrk)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_arrayptr_get_at_gint:
/*
letpush(beg)
*/
/* (*nothing*) */
/*
letpush(end)
*/

ATSINSmove(tmp6, PMVtmpltcst(ptr1_add_gint<S2Evar(a(5249))><S2Evar(tk(5250))>)(ATSPMVcastfn(arrayptr2ptr, atstkind_type(atstype_ptrk), ATSPMVrefarg0(arg0)), arg1)) ;
ATSINSmove(tmpret5, PMVtmpltcst(ptr0_get<S2Evar(a(5249))>)(tmp6)) ;
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn(tmpret5) ;
} /* end of [ATSLIB_056$prelude_arrayptr_get_at_gint$1] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/arrayptr.dats: 4515(line=219, offs=3) -- 4604(line=222, offs=4)
*/
/*
local: 
global: arrayptr_get_at_gint$1$1(level=1)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = a(5249), tk(5250)
tmparg = S2Evar(a(5249)); S2Evar(tk(5250))
tmpsub = Some(a(5249) -> S2Eapp(S2Ecst(g0int_t0ype); S2Ecst(int_kind)); tk(5250) -> S2Eextkind(atstype_int))
*/
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_arrayptr_get_at_gint$1$1(atsrefarg0_type(atstkind_type(atstype_ptrk)) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp5$1, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp6$1, atstkind_type(atstype_ptrk)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_arrayptr_get_at_gint:
/*
letpush(beg)
*/
/* (*nothing*) */
/*
letpush(end)
*/

ATSINSmove(tmp6$1, ATSLIB_056$prelude_ptr1_add_gint$3$1(ATSPMVcastfn(arrayptr2ptr, atstkind_type(atstype_ptrk), ATSPMVrefarg0(arg0)), arg1)) ;
ATSINSmove(tmp5$1, ATSLIB_056$prelude_056$unsafe_ptr0_get$9$1(tmp6$1)) ;
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn(tmp5$1) ;
} /* end of [ATSLIB_056$prelude_arrayptr_get_at_gint$1$1] */

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/pointer.dats: 2518(line=93, offs=22) -- 2585(line=94, offs=59)
*/
/*
local: 
global: ptr1_add_gint$3$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = a(3542), tk(3543)
tmparg = S2Evar(a(3542)); S2Evar(tk(3543))
tmpsub = None()
*/
atstkind_type(atstype_ptrk)
ATSLIB_056$prelude_ptr1_add_gint$3(atstkind_type(atstype_ptrk) arg0, atstkind_t0ype(atstyvar_type(tk)) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret9, atstkind_type(atstype_ptrk)) ;
ATStmpdec(tmp10, atstkind_type(atstype_ptrk)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_ptr1_add_gint:
ATSINSmove(tmp10, PMVtmpltcst(ptr0_add_gint<S2Evar(a(3542))><S2Evar(tk(3543))>)(arg0, arg1)) ;
ATSINSmove(tmpret9, ATSPMVcastfn(cast, atstkind_type(atstype_ptrk), tmp10)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret9) ;
} /* end of [ATSLIB_056$prelude_ptr1_add_gint$3] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/pointer.dats: 2518(line=93, offs=22) -- 2585(line=94, offs=59)
*/
/*
local: 
global: ptr1_add_gint$3$1(level=2)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = a(3542), tk(3543)
tmparg = S2Evar(a(3542)); S2Evar(tk(3543))
tmpsub = Some(a(3542) -> S2Eapp(S2Ecst(g0int_t0ype); S2Ecst(int_kind)); tk(3543) -> S2Eextkind(atstype_int))
*/
atstkind_type(atstype_ptrk)
ATSLIB_056$prelude_ptr1_add_gint$3$1(atstkind_type(atstype_ptrk) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp9$1, atstkind_type(atstype_ptrk)) ;
ATStmpdec(tmp10$1, atstkind_type(atstype_ptrk)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_ptr1_add_gint:
ATSINSmove(tmp10$1, ATSLIB_056$prelude_ptr0_add_gint$5$1(arg0, arg1)) ;
ATSINSmove(tmp9$1, ATSPMVcastfn(cast, atstkind_type(atstype_ptrk), tmp10$1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp9$1) ;
} /* end of [ATSLIB_056$prelude_ptr1_add_gint$3$1] */

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/pointer.dats: 1952(line=68, offs=15) -- 2005(line=69, offs=45)
*/
/*
local: 
global: ptr0_add_gint$5$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = a(3528), tk(3529)
tmparg = S2Evar(a(3528)); S2Evar(tk(3529))
tmpsub = None()
*/
atstkind_type(atstype_ptrk)
ATSLIB_056$prelude_ptr0_add_gint$5(atstkind_type(atstype_ptrk) arg0, atstkind_t0ype(atstyvar_type(tk)) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret13, atstkind_type(atstype_ptrk)) ;
ATStmpdec(tmp14, atstkind_t0ype(atstype_size)) ;
ATStmpdec(tmp15, atstkind_t0ype(atstype_size)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_ptr0_add_gint:
ATSINSmove(tmp15, PMVtmpltcst(g0int2uint<S2Evar(tk(3529)), S2Eextkind(atstype_size)>)(arg1)) ;
ATSINSmove(tmp14, PMVtmpltcst(g0uint_mul<S2Eextkind(atstype_size)>)(tmp15, ATSPMVsizeof(atstyvar_type(a)))) ;
ATSINSmove(tmpret13, atspre_add_ptr0_bsz(arg0, tmp14)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret13) ;
} /* end of [ATSLIB_056$prelude_ptr0_add_gint$5] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/pointer.dats: 1952(line=68, offs=15) -- 2005(line=69, offs=45)
*/
/*
local: 
global: ptr0_add_gint$5$1(level=3)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = a(3528), tk(3529)
tmparg = S2Evar(a(3528)); S2Evar(tk(3529))
tmpsub = Some(a(3528) -> S2Eapp(S2Ecst(g0int_t0ype); S2Ecst(int_kind)); tk(3529) -> S2Eextkind(atstype_int))
*/
atstkind_type(atstype_ptrk)
ATSLIB_056$prelude_ptr0_add_gint$5$1(atstkind_type(atstype_ptrk) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp13$1, atstkind_type(atstype_ptrk)) ;
ATStmpdec(tmp14$1, atstkind_t0ype(atstype_size)) ;
ATStmpdec(tmp15$1, atstkind_t0ype(atstype_size)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_ptr0_add_gint:
ATSINSmove(tmp15$1, atspre_g0int2uint_int_size(arg1)) ;
ATSINSmove(tmp14$1, atspre_g0uint_mul_size(tmp15$1, ATSPMVsizeof(atstkind_t0ype(atstype_int)))) ;
ATSINSmove(tmp13$1, atspre_add_ptr0_bsz(arg0, tmp14$1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp13$1) ;
} /* end of [ATSLIB_056$prelude_ptr0_add_gint$5$1] */

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/unsafe.dats: 1621(line=51, offs=3) -- 1834(line=59, offs=2)
*/
/*
local: 
global: ptr0_get$9$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = a(3798)
tmparg = S2Evar(a(3798))
tmpsub = None()
*/
atstyvar_type(a)
ATSLIB_056$prelude_056$unsafe_ptr0_get$9(atstkind_type(atstype_ptrk) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret19, atstyvar_type(a)) ;
ATStmpdec(tmp20, atstyvar_type(a)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_ptr0_get:
/*
letpush(beg)
*/
/* (*nothing*) */
/* (*nothing*) */
ATSINSmove(tmp20, ATSderef(ATSPMVcastfn(g1ofg0_ptr, atstkind_type(atstype_ptrk), arg0), atstyvar_type(a))) ;
/* (*nothing*) */
/*
letpush(end)
*/

ATSINSmove(tmpret19, tmp20) ;
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn(tmpret19) ;
} /* end of [ATSLIB_056$prelude_056$unsafe_ptr0_get$9] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/unsafe.dats: 1621(line=51, offs=3) -- 1834(line=59, offs=2)
*/
/*
local: 
global: ptr0_get$9$1(level=2)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = a(3798)
tmparg = S2Evar(a(3798))
tmpsub = Some(a(3798) -> S2Eapp(S2Ecst(g0int_t0ype); S2Ecst(int_kind)))
*/
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_056$unsafe_ptr0_get$9$1(atstkind_type(atstype_ptrk) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp19$1, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp20$1, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_ptr0_get:
/*
letpush(beg)
*/
/* (*nothing*) */
/* (*nothing*) */
ATSINSmove(tmp20$1, ATSderef(ATSPMVcastfn(g1ofg0_ptr, atstkind_type(atstype_ptrk), arg0), atstkind_t0ype(atstype_int))) ;
/* (*nothing*) */
/*
letpush(end)
*/

ATSINSmove(tmp19$1, tmp20$1) ;
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn(tmp19$1) ;
} /* end of [ATSLIB_056$prelude_056$unsafe_ptr0_get$9$1] */

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 27633(line=742, offs=14) -- 27673(line=742, offs=54)
*/
/*
local: 
global: eq_g0int_int$11$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = tk(3260)
tmparg = S2Evar(tk(3260))
tmpsub = None()
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g0int_int$11(atstkind_t0ype(atstyvar_type(tk)) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret25, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp26, atstkind_t0ype(atstyvar_type(tk))) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_eq_g0int_int:
ATSINSmove(tmp26, PMVtmpltcst(g0int2int<S2Eextkind(atstype_int), S2Evar(tk(3260))>)(arg1)) ;
ATSINSmove(tmpret25, PMVtmpltcst(g0int_eq<S2Evar(tk(3260))>)(arg0, tmp26)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret25) ;
} /* end of [ATSLIB_056$prelude_eq_g0int_int$11] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 27633(line=742, offs=14) -- 27673(line=742, offs=54)
*/
/*
local: 
global: eq_g0int_int$11$1(level=1)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = tk(3260)
tmparg = S2Evar(tk(3260))
tmpsub = Some(tk(3260) -> S2Eextkind(atstype_int))
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g0int_int$11$1(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp25$1, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp26$1, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_eq_g0int_int:
ATSINSmove(tmp26$1, atspre_g0int2int_int_int(arg1)) ;
ATSINSmove(tmp25$1, atspre_g0int_eq_int(arg0, tmp26$1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp25$1) ;
} /* end of [ATSLIB_056$prelude_eq_g0int_int$11$1] */

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test23_array.dats: 254(line=16, offs=17) -- 265(line=16, offs=28)
*/
/*
local: foo_0$0(level=0)
global: foo_0$0(level=0), mainats_void_0$15$0(level=0)
local: 
global: 
*/
ATSglobaldec()
atsvoid_t0ype
mainats_void_0()
{
/* tmpvardeclst(beg) */
ATStmpdec_void(tmpret30, atsvoid_t0ype) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_main_void_0:
ATSINSmove_void(tmpret30, foo_0()) ;
/* funbodyinstrlst(end) */
ATSreturn_void(tmpret30) ;
} /* end of [mainats_void_0] */


/*
** for initialization(dynloading)
*/
atsvoid_t0ype
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test23_array_056$dats__dynload()
{
ATSdynload0(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test23_array_056$dats__dynloadflag
) ;
ATSif(
ATSCKiseqz(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test23_array_056$dats__dynloadflag
)
) ATSthen() {
ATSdynloadset(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test23_array_056$dats__dynloadflag) ;
/*
dynexnlst-initize(beg)
*/
/*
dynexnlst-initize(end)
*/
} /* ATSendif */
ATSreturn_void() ;
} /* end of [*_dynload] */

/*
** the ATS runtime
*/
#ifndef _ATS_CCOMP_RUNTIME_NONE
#include "pats_ccomp_runtime.c"
#include "pats_ccomp_runtime2_dats.c"
#include "pats_ccomp_runtime_trywith.c"
#endif /* _ATS_CCOMP_RUNTIME_NONE */

/*
** the [main] implementation
*/
int
main
(
int argc, char **argv, char **envp
) {
int err = 0 ;
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test23_array_056$dats__dynload() ;
ATSmainats_void_0(err) ;
return (err) ;
} /* end of [main] */
