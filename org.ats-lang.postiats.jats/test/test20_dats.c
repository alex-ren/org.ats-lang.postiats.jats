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
typedef
struct {
atstkind_t0ype(atstype_int) atslab$x1; 
atstkind_t0ype(atstype_int) atslab$x2; 
} postiats_tyrec_0 ;
typedef
struct {
atstkind_t0ype(atstype_int) atslab$x1; 
postiats_tyrec_0 atslab$x2; 
} postiats_tyrec_1 ;
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
ATSdyncst_mac(atspre_assert_errmsg_bool1) ;
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
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g1int_int$1(atstkind_t0ype(atstyvar_type(tk)), atstkind_t0ype(atstype_int)) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g1int_int$1$1(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

ATSstaticdec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g1int_int$1$2(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

ATSstaticdec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g1int_int$1$3(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

#if(0)
ATSglobaldec()
atsvoid_t0ype
mainats_void_0() ;
#endif // end of [QUALIFIED]

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test20.dats: 129(line=10, offs=5) -- 377(line=22, offs=2)
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
ATStmpdec(tmpref1, postiats_tyrec_1) ;
ATStmpdec(tmp2, postiats_tyrec_0) ;
ATStmpdec(tmp3, postiats_tyrec_0) ;
ATStmpdec_void(tmp4, atsvoid_t0ype) ;
ATStmpdec(tmp5, atstkind_t0ype(atstype_bool)) ;
ATStmpdec_void(tmp10, atsvoid_t0ype) ;
ATStmpdec(tmp11, atstkind_t0ype(atstype_bool)) ;
ATStmpdec_void(tmp14, atsvoid_t0ype) ;
ATStmpdec(tmp15, atstkind_t0ype(atstype_bool)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_foo_0:
/*
letpush(beg)
*/
/*
ATSINStmpdec(tmpref1)) ;
*/
ATSINSstore_fltrec_ofs (tmp2, postiats_tyrec_0, atslab$x1, ATSPMVi0nt(2)) ;
ATSINSstore_fltrec_ofs (tmp2, postiats_tyrec_0, atslab$x2, ATSPMVi0nt(3)) ;
ATSINSstore_fltrec_ofs (tmpref1, postiats_tyrec_1, atslab$x1, ATSPMVi0nt(1)) ;
ATSINSstore_fltrec_ofs (tmpref1, postiats_tyrec_1, atslab$x2, tmp2) ;
ATSINSstore(ATSSELfltrec(tmpref1, postiats_tyrec_1, atslab$x1), ATSPMVi0nt(11)) ;
ATSINSstore(ATSSELfltrec(ATSSELfltrec(tmpref1, postiats_tyrec_1, atslab$x2), postiats_tyrec_0, atslab$x1), ATSPMVi0nt(22)) ;
ATSINSstore_fltrec_ofs (tmp3, postiats_tyrec_0, atslab$x1, ATSPMVi0nt(22)) ;
ATSINSstore_fltrec_ofs (tmp3, postiats_tyrec_0, atslab$x2, ATSPMVi0nt(33)) ;
ATSINSstore(ATSSELfltrec(tmpref1, postiats_tyrec_1, atslab$x2), tmp3) ;
ATSINSmove(tmp5, ATSLIB_056$prelude_eq_g1int_int$1$1(ATSSELfltrec(tmpref1, postiats_tyrec_1, atslab$x1), ATSPMVi0nt(11))) ;
ATSINSmove_void(tmp4, atspre_assert_errmsg_bool1(tmp5, ATSCSTSPmyloc("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test20.dats: 280(line=16, offs=10) -- 301(line=16, offs=31)"))) ;
ATSINSmove(tmp11, ATSLIB_056$prelude_eq_g1int_int$1$2(ATSSELfltrec(ATSSELfltrec(tmpref1, postiats_tyrec_1, atslab$x2), postiats_tyrec_0, atslab$x1), ATSPMVi0nt(22))) ;
ATSINSmove_void(tmp10, atspre_assert_errmsg_bool1(tmp11, ATSCSTSPmyloc("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test20.dats: 312(line=17, offs=10) -- 336(line=17, offs=34)"))) ;
ATSINSmove(tmp15, ATSLIB_056$prelude_eq_g1int_int$1$3(ATSSELfltrec(ATSSELfltrec(tmpref1, postiats_tyrec_1, atslab$x2), postiats_tyrec_0, atslab$x2), ATSPMVi0nt(33))) ;
ATSINSmove_void(tmp14, atspre_assert_errmsg_bool1(tmp15, ATSCSTSPmyloc("/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test20.dats: 347(line=18, offs=10) -- 371(line=18, offs=34)"))) ;
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
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 28148(line=760, offs=14) -- 28188(line=760, offs=54)
*/
/*
local: 
global: eq_g1int_int$1$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = tk(3275)
tmparg = S2Evar(tk(3275))
tmpsub = None()
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g1int_int$1(atstkind_t0ype(atstyvar_type(tk)) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret6, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp7, atstkind_t0ype(atstyvar_type(tk))) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_eq_g1int_int:
ATSINSmove(tmp7, PMVtmpltcst(g1int2int<S2Eextkind(atstype_int), S2Evar(tk(3275))>)(arg1)) ;
ATSINSmove(tmpret6, PMVtmpltcst(g1int_eq<S2Evar(tk(3275))>)(arg0, tmp7)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret6) ;
} /* end of [ATSLIB_056$prelude_eq_g1int_int$1] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 28148(line=760, offs=14) -- 28188(line=760, offs=54)
*/
/*
local: 
global: eq_g1int_int$1$1(level=1)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = tk(3275)
tmparg = S2Evar(tk(3275))
tmpsub = Some(tk(3275) -> S2Eextkind(atstype_int))
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g1int_int$1$1(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp6$1, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp7$1, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_eq_g1int_int:
ATSINSmove(tmp7$1, atspre_g1int2int_int_int(arg1)) ;
ATSINSmove(tmp6$1, atspre_g1int_eq_int(arg0, tmp7$1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp6$1) ;
} /* end of [ATSLIB_056$prelude_eq_g1int_int$1$1] */

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 28148(line=760, offs=14) -- 28188(line=760, offs=54)
*/
/*
local: 
global: eq_g1int_int$1$2(level=1)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = tk(3275)
tmparg = S2Evar(tk(3275))
tmpsub = Some(tk(3275) -> S2Eextkind(atstype_int))
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g1int_int$1$2(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp6$2, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp7$2, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_eq_g1int_int:
ATSINSmove(tmp7$2, atspre_g1int2int_int_int(arg1)) ;
ATSINSmove(tmp6$2, atspre_g1int_eq_int(arg0, tmp7$2)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp6$2) ;
} /* end of [ATSLIB_056$prelude_eq_g1int_int$1$2] */

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 28148(line=760, offs=14) -- 28188(line=760, offs=54)
*/
/*
local: 
global: eq_g1int_int$1$3(level=1)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = tk(3275)
tmparg = S2Evar(tk(3275))
tmpsub = Some(tk(3275) -> S2Eextkind(atstype_int))
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_eq_g1int_int$1$3(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp6$3, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp7$3, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_eq_g1int_int:
ATSINSmove(tmp7$3, atspre_g1int2int_int_int(arg1)) ;
ATSINSmove(tmp6$3, atspre_g1int_eq_int(arg0, tmp7$3)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp6$3) ;
} /* end of [ATSLIB_056$prelude_eq_g1int_int$1$3] */

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/test20.dats: 411(line=24, offs=17) -- 422(line=24, offs=28)
*/
/*
local: foo_0$0(level=0)
global: foo_0$0(level=0), mainats_void_0$7$0(level=0)
local: 
global: 
*/
ATSglobaldec()
atsvoid_t0ype
mainats_void_0()
{
/* tmpvardeclst(beg) */
ATStmpdec_void(tmpret18, atsvoid_t0ype) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_main_void_0:
ATSINSmove_void(tmpret18, foo_0()) ;
/* funbodyinstrlst(end) */
ATSreturn_void(tmpret18) ;
} /* end of [mainats_void_0] */


/*
** for initialization(dynloading)
*/
atsvoid_t0ype
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test20_056$dats__dynload()
{
ATSdynload0(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test20_056$dats__dynloadflag
) ;
ATSif(
ATSCKiseqz(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test20_056$dats__dynloadflag
)
) ATSthen() {
ATSdynloadset(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test20_056$dats__dynloadflag) ;
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
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$test20_056$dats__dynload() ;
ATSmainats_void_0(err) ;
return (err) ;
} /* end of [main] */
