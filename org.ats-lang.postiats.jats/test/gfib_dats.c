/*
**
** The C code is generated by ATS/Postiats
** The compilation time is: 2013-7-3: 16h:41m
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
ATSdyncst_mac(atspre_print_string) ;
ATSdyncst_mac(atspre_print_int) ;
ATSdyncst_mac(atspre_g0int2int_int_int) ;
ATSdyncst_mac(atspre_print_newline) ;
ATSdyncst_mac(atspre_print_double) ;
ATSdyncst_mac(atspre_g0int2float_int_double) ;
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
#if(0)
#if(0)
ATSglobaldec()
atstyvar_type(a)
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0(atstyvar_type(a)) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_int)
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$1(atstkind_t0ype(atstype_int)) ;

#if(0)
#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_gnumint$3(atstkind_t0ype(atstype_int)) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_gnumint$3$1(atstkind_t0ype(atstype_int)) ;

#if(0)
#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_ggte_val$5(atstyvar_type(a), atstyvar_type(a)) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_ggte_val$5$1(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

#if(0)
#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_gte_g0int_int$8(atstkind_t0ype(atstyvar_type(tk)), atstkind_t0ype(atstype_int)) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_gte_g0int_int$8$1(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

ATSstaticdec()
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_gnumint$3$2(atstkind_t0ype(atstype_int)) ;

#if(0)
#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_gsub_val$13(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_gsub_val$13$1(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

ATSstaticdec()
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_gnumint$3$3(atstkind_t0ype(atstype_int)) ;

ATSstaticdec()
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_gsub_val$13$2(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

#if(0)
#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_gadd_val$18(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_gadd_val$18$1(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

ATSstaticdec()
atstkind_t0ype(atstype_double)
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$2(atstkind_t0ype(atstype_double)) ;

#if(0)
#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_double)
ATSLIB_056$prelude_gnumint$22(atstkind_t0ype(atstype_int)) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_double)
ATSLIB_056$prelude_gnumint$22$1(atstkind_t0ype(atstype_int)) ;

ATSstaticdec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_ggte_val$5$2(atstkind_t0ype(atstype_double), atstkind_t0ype(atstype_double)) ;

ATSstaticdec()
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_gte_g0int_int$8$2(atstkind_t0ype(atstype_int), atstkind_t0ype(atstype_int)) ;

ATSstaticdec()
atstkind_t0ype(atstype_double)
ATSLIB_056$prelude_gnumint$22$2(atstkind_t0ype(atstype_int)) ;

#if(0)
#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_double)
ATSLIB_056$prelude_gsub_val$28(atstkind_t0ype(atstype_double), atstkind_t0ype(atstype_double)) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_double)
ATSLIB_056$prelude_gsub_val$28$1(atstkind_t0ype(atstype_double), atstkind_t0ype(atstype_double)) ;

ATSstaticdec()
atstkind_t0ype(atstype_double)
ATSLIB_056$prelude_gnumint$22$3(atstkind_t0ype(atstype_int)) ;

ATSstaticdec()
atstkind_t0ype(atstype_double)
ATSLIB_056$prelude_gsub_val$28$2(atstkind_t0ype(atstype_double), atstkind_t0ype(atstype_double)) ;

#if(0)
#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_double)
ATSLIB_056$prelude_gadd_val$33(atstkind_t0ype(atstype_double), atstkind_t0ype(atstype_double)) ;
#endif // end of [QUALIFIED]
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_double)
ATSLIB_056$prelude_gadd_val$33$1(atstkind_t0ype(atstype_double), atstkind_t0ype(atstype_double)) ;

#if(0)
ATSglobaldec()
atsvoid_t0ype
mainats_void_0() ;
#endif // end of [QUALIFIED]

#if(0)
/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/gfib.dats: 447(line=29, offs=6) -- 747(line=45, offs=4)
*/
/*
local: 
global: gfib$0$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = a(3212)
tmparg = S2Evar(a(3212))
tmpsub = None()
*/
atstyvar_type(a)
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0(atstyvar_type(a) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret0, atstyvar_type(a)) ;
ATStmpdec(tmp1, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp2, atstyvar_type(a)) ;
ATStmpdec(tmp3, atstyvar_type(a)) ;
ATStmpdec(tmp4, atstyvar_type(a)) ;
ATStmpdec(tmp5, atstyvar_type(a)) ;
ATStmpdec(tmp6, atstyvar_type(a)) ;
ATStmpdec(tmp7, atstyvar_type(a)) ;
ATStmpdec(tmp8, atstyvar_type(a)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gfib:
/*
letpush(beg)
*/
/*
letpush(end)
*/

ATSINSmove(tmp2, PMVtmpltcst(gnumint<S2Evar(a(3212))>)(ATSPMVi0nt(2))) ;
ATSINSmove(tmp1, PMVtmpltcst(ggte_val<S2Evar(a(3212))>)(arg0, tmp2)) ;
ATSif(
tmp1
) ATSthen() {
ATSINSmove(tmp5, PMVtmpltcst(gnumint<S2Evar(a(3212))>)(ATSPMVi0nt(1))) ;
ATSINSmove(tmp4, PMVtmpltcst(gsub_val<S2Evar(a(3212))>)(arg0, tmp5)) ;
ATSINSmove(tmp3, PMVtmpltcst(gfib<S2Evar(a(3212))>)(tmp4)) ;
ATSINSmove(tmp8, PMVtmpltcst(gnumint<S2Evar(a(3212))>)(ATSPMVi0nt(2))) ;
ATSINSmove(tmp7, PMVtmpltcst(gsub_val<S2Evar(a(3212))>)(arg0, tmp8)) ;
ATSINSmove(tmp6, PMVtmpltcst(gfib<S2Evar(a(3212))>)(tmp7)) ;
ATSINSmove(tmpret0, PMVtmpltcst(gadd_val<S2Evar(a(3212))>)(tmp3, tmp6)) ;
} ATSelse() {
ATSINSmove(tmpret0, arg0) ;
} /* ATSendif */
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn(tmpret0) ;
} /* end of [_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/gfib.dats: 447(line=29, offs=6) -- 747(line=45, offs=4)
*/
/*
local: 
global: gfib$0$1(level=1)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = a(3212)
tmparg = S2Evar(a(3212))
tmpsub = Some(a(3212) -> S2Eapp(S2Ecst(g0int_t0ype); S2Ecst(int_kind)))
*/
atstkind_t0ype(atstype_int)
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$1(atstkind_t0ype(atstype_int) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp0$1, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp1$1, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp2$1, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp3$1, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp4$1, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp5$1, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp6$1, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp7$1, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp8$1, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gfib:
/*
letpush(beg)
*/
/*
letpush(end)
*/

ATSINSmove(tmp2$1, ATSLIB_056$prelude_gnumint$3$1(ATSPMVi0nt(2))) ;
ATSINSmove(tmp1$1, ATSLIB_056$prelude_ggte_val$5$1(arg0, tmp2$1)) ;
ATSif(
tmp1$1
) ATSthen() {
ATSINSmove(tmp5$1, ATSLIB_056$prelude_gnumint$3$2(ATSPMVi0nt(1))) ;
ATSINSmove(tmp4$1, ATSLIB_056$prelude_gsub_val$13$1(arg0, tmp5$1)) ;
ATSINSmove(tmp3$1, _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$1(tmp4$1)) ;
ATSINSmove(tmp8$1, ATSLIB_056$prelude_gnumint$3$3(ATSPMVi0nt(2))) ;
ATSINSmove(tmp7$1, ATSLIB_056$prelude_gsub_val$13$2(arg0, tmp8$1)) ;
ATSINSmove(tmp6$1, _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$1(tmp7$1)) ;
ATSINSmove(tmp0$1, ATSLIB_056$prelude_gadd_val$18$1(tmp3$1, tmp6$1)) ;
} ATSelse() {
ATSINSmove(tmp0$1, arg0) ;
} /* ATSendif */
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn(tmp0$1) ;
} /* end of [_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$1] */

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gnumber.dats: 1608(line=49, offs=14) -- 1634(line=49, offs=40)
*/
/*
local: 
global: gnumint$3$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = 
tmparg = S2Eapp(S2Ecst(g0int_t0ype); S2Ecst(int_kind))
tmpsub = None()
*/
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_gnumint$3(atstkind_t0ype(atstype_int) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret22, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gnumint:
ATSINSmove(tmpret22, atspre_g0int2int_int_int(arg0)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret22) ;
} /* end of [ATSLIB_056$prelude_gnumint$3] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gnumber.dats: 1608(line=49, offs=14) -- 1634(line=49, offs=40)
*/
/*
local: 
global: gnumint$3$1(level=2)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = 
tmparg = S2Eapp(S2Ecst(g0int_t0ype); S2Ecst(int_kind))
tmpsub = Some()
*/
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_gnumint$3$1(atstkind_t0ype(atstype_int) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp22$1, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gnumint:
ATSINSmove(tmp22$1, atspre_g0int2int_int_int(arg0)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp22$1) ;
} /* end of [ATSLIB_056$prelude_gnumint$3$1] */

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gorder.dats: 1727(line=51, offs=10) -- 1763(line=51, offs=46)
*/
/*
local: 
global: ggte_val$5$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = a(3201)
tmparg = S2Evar(a(3201))
tmpsub = None()
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_ggte_val$5(atstyvar_type(a) arg0, atstyvar_type(a) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret24, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp25, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_ggte_val:
ATSINSmove(tmp25, PMVtmpltcst(gcompare_val<S2Evar(a(3201))>)(arg0, arg1)) ;
ATSINSmove(tmpret24, PMVtmpltcst(gte_g0int_int<S2Eextkind(atstype_int)>)(tmp25, ATSPMVi0nt(0))) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret24) ;
} /* end of [ATSLIB_056$prelude_ggte_val$5] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gorder.dats: 1727(line=51, offs=10) -- 1763(line=51, offs=46)
*/
/*
local: 
global: ggte_val$5$1(level=2)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = a(3201)
tmparg = S2Evar(a(3201))
tmpsub = Some(a(3201) -> S2Eapp(S2Ecst(g0int_t0ype); S2Ecst(int_kind)))
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_ggte_val$5$1(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp24$1, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp25$1, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_ggte_val:
ATSINSmove(tmp25$1, atspre_g0int_compare_int(arg0, arg1)) ;
ATSINSmove(tmp24$1, ATSLIB_056$prelude_gte_g0int_int$8$1(tmp25$1, ATSPMVi0nt(0))) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp24$1) ;
} /* end of [ATSLIB_056$prelude_ggte_val$5$1] */

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 27564(line=740, offs=15) -- 27605(line=740, offs=56)
*/
/*
local: 
global: gte_g0int_int$8$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = tk(3149)
tmparg = S2Evar(tk(3149))
tmpsub = None()
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_gte_g0int_int$8(atstkind_t0ype(atstyvar_type(tk)) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret28, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp29, atstkind_t0ype(atstyvar_type(tk))) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gte_g0int_int:
ATSINSmove(tmp29, PMVtmpltcst(g0int2int<S2Eextkind(atstype_int), S2Evar(tk(3149))>)(arg1)) ;
ATSINSmove(tmpret28, PMVtmpltcst(g0int_gte<S2Evar(tk(3149))>)(arg0, tmp29)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret28) ;
} /* end of [ATSLIB_056$prelude_gte_g0int_int$8] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 27564(line=740, offs=15) -- 27605(line=740, offs=56)
*/
/*
local: 
global: gte_g0int_int$8$1(level=3)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = tk(3149)
tmparg = S2Evar(tk(3149))
tmpsub = Some(tk(3149) -> S2Eextkind(atstype_int))
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_gte_g0int_int$8$1(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp28$1, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp29$1, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gte_g0int_int:
ATSINSmove(tmp29$1, atspre_g0int2int_int_int(arg1)) ;
ATSINSmove(tmp28$1, atspre_g0int_gte_int(arg0, tmp29$1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp28$1) ;
} /* end of [ATSLIB_056$prelude_gte_g0int_int$8$1] */

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gnumber.dats: 1608(line=49, offs=14) -- 1634(line=49, offs=40)
*/
/*
local: 
global: gnumint$3$2(level=2)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = 
tmparg = S2Eapp(S2Ecst(g0int_t0ype); S2Ecst(int_kind))
tmpsub = Some()
*/
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_gnumint$3$2(atstkind_t0ype(atstype_int) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp22$2, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gnumint:
ATSINSmove(tmp22$2, atspre_g0int2int_int_int(arg0)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp22$2) ;
} /* end of [ATSLIB_056$prelude_gnumint$3$2] */

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gnumber.dats: 1823(line=63, offs=15) -- 1837(line=63, offs=29)
*/
/*
local: 
global: gsub_val$13$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = 
tmparg = S2Eapp(S2Ecst(g0int_t0ype); S2Ecst(int_kind))
tmpsub = None()
*/
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_gsub_val$13(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret33, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gsub_val:
ATSINSmove(tmpret33, PMVtmpltcst(g0int_sub<S2Eextkind(atstype_int)>)(arg0, arg1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret33) ;
} /* end of [ATSLIB_056$prelude_gsub_val$13] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gnumber.dats: 1823(line=63, offs=15) -- 1837(line=63, offs=29)
*/
/*
local: 
global: gsub_val$13$1(level=2)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = 
tmparg = S2Eapp(S2Ecst(g0int_t0ype); S2Ecst(int_kind))
tmpsub = Some()
*/
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_gsub_val$13$1(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp33$1, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gsub_val:
ATSINSmove(tmp33$1, atspre_g0int_sub_int(arg0, arg1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp33$1) ;
} /* end of [ATSLIB_056$prelude_gsub_val$13$1] */

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gnumber.dats: 1608(line=49, offs=14) -- 1634(line=49, offs=40)
*/
/*
local: 
global: gnumint$3$3(level=2)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = 
tmparg = S2Eapp(S2Ecst(g0int_t0ype); S2Ecst(int_kind))
tmpsub = Some()
*/
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_gnumint$3$3(atstkind_t0ype(atstype_int) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp22$3, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gnumint:
ATSINSmove(tmp22$3, atspre_g0int2int_int_int(arg0)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp22$3) ;
} /* end of [ATSLIB_056$prelude_gnumint$3$3] */

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gnumber.dats: 1823(line=63, offs=15) -- 1837(line=63, offs=29)
*/
/*
local: 
global: gsub_val$13$2(level=2)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = 
tmparg = S2Eapp(S2Ecst(g0int_t0ype); S2Ecst(int_kind))
tmpsub = Some()
*/
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_gsub_val$13$2(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp33$2, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gsub_val:
ATSINSmove(tmp33$2, atspre_g0int_sub_int(arg0, arg1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp33$2) ;
} /* end of [ATSLIB_056$prelude_gsub_val$13$2] */

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gnumber.dats: 1784(line=61, offs=15) -- 1798(line=61, offs=29)
*/
/*
local: 
global: gadd_val$18$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = 
tmparg = S2Eapp(S2Ecst(g0int_t0ype); S2Ecst(int_kind))
tmpsub = None()
*/
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_gadd_val$18(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret37, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gadd_val:
ATSINSmove(tmpret37, PMVtmpltcst(g0int_add<S2Eextkind(atstype_int)>)(arg0, arg1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret37) ;
} /* end of [ATSLIB_056$prelude_gadd_val$18] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gnumber.dats: 1784(line=61, offs=15) -- 1798(line=61, offs=29)
*/
/*
local: 
global: gadd_val$18$1(level=2)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = 
tmparg = S2Eapp(S2Ecst(g0int_t0ype); S2Ecst(int_kind))
tmpsub = Some()
*/
atstkind_t0ype(atstype_int)
ATSLIB_056$prelude_gadd_val$18$1(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp37$1, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gadd_val:
ATSINSmove(tmp37$1, atspre_g0int_add_int(arg0, arg1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp37$1) ;
} /* end of [ATSLIB_056$prelude_gadd_val$18$1] */

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/gfib.dats: 447(line=29, offs=6) -- 747(line=45, offs=4)
*/
/*
local: 
global: gfib$0$2(level=1)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = a(3212)
tmparg = S2Evar(a(3212))
tmpsub = Some(a(3212) -> S2Eapp(S2Ecst(g0float_t0ype); S2Ecst(double_kind)))
*/
atstkind_t0ype(atstype_double)
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$2(atstkind_t0ype(atstype_double) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp0$2, atstkind_t0ype(atstype_double)) ;
ATStmpdec(tmp1$2, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp2$2, atstkind_t0ype(atstype_double)) ;
ATStmpdec(tmp3$2, atstkind_t0ype(atstype_double)) ;
ATStmpdec(tmp4$2, atstkind_t0ype(atstype_double)) ;
ATStmpdec(tmp5$2, atstkind_t0ype(atstype_double)) ;
ATStmpdec(tmp6$2, atstkind_t0ype(atstype_double)) ;
ATStmpdec(tmp7$2, atstkind_t0ype(atstype_double)) ;
ATStmpdec(tmp8$2, atstkind_t0ype(atstype_double)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gfib:
/*
letpush(beg)
*/
/*
letpush(end)
*/

ATSINSmove(tmp2$2, ATSLIB_056$prelude_gnumint$22$1(ATSPMVi0nt(2))) ;
ATSINSmove(tmp1$2, ATSLIB_056$prelude_ggte_val$5$2(arg0, tmp2$2)) ;
ATSif(
tmp1$2
) ATSthen() {
ATSINSmove(tmp5$2, ATSLIB_056$prelude_gnumint$22$2(ATSPMVi0nt(1))) ;
ATSINSmove(tmp4$2, ATSLIB_056$prelude_gsub_val$28$1(arg0, tmp5$2)) ;
ATSINSmove(tmp3$2, _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$2(tmp4$2)) ;
ATSINSmove(tmp8$2, ATSLIB_056$prelude_gnumint$22$3(ATSPMVi0nt(2))) ;
ATSINSmove(tmp7$2, ATSLIB_056$prelude_gsub_val$28$2(arg0, tmp8$2)) ;
ATSINSmove(tmp6$2, _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$2(tmp7$2)) ;
ATSINSmove(tmp0$2, ATSLIB_056$prelude_gadd_val$33$1(tmp3$2, tmp6$2)) ;
} ATSelse() {
ATSINSmove(tmp0$2, arg0) ;
} /* ATSendif */
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn(tmp0$2) ;
} /* end of [_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$2] */

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gnumber.dats: 2078(line=78, offs=17) -- 2109(line=78, offs=48)
*/
/*
local: 
global: gnumint$22$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = 
tmparg = S2Eapp(S2Ecst(g0float_t0ype); S2Ecst(double_kind))
tmpsub = None()
*/
atstkind_t0ype(atstype_double)
ATSLIB_056$prelude_gnumint$22(atstkind_t0ype(atstype_int) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret52, atstkind_t0ype(atstype_double)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gnumint:
ATSINSmove(tmpret52, atspre_g0int2float_int_double(arg0)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret52) ;
} /* end of [ATSLIB_056$prelude_gnumint$22] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gnumber.dats: 2078(line=78, offs=17) -- 2109(line=78, offs=48)
*/
/*
local: 
global: gnumint$22$1(level=2)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = 
tmparg = S2Eapp(S2Ecst(g0float_t0ype); S2Ecst(double_kind))
tmpsub = Some()
*/
atstkind_t0ype(atstype_double)
ATSLIB_056$prelude_gnumint$22$1(atstkind_t0ype(atstype_int) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp52$1, atstkind_t0ype(atstype_double)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gnumint:
ATSINSmove(tmp52$1, atspre_g0int2float_int_double(arg0)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp52$1) ;
} /* end of [ATSLIB_056$prelude_gnumint$22$1] */

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gorder.dats: 1727(line=51, offs=10) -- 1763(line=51, offs=46)
*/
/*
local: 
global: ggte_val$5$2(level=2)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = a(3201)
tmparg = S2Evar(a(3201))
tmpsub = Some(a(3201) -> S2Eapp(S2Ecst(g0float_t0ype); S2Ecst(double_kind)))
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_ggte_val$5$2(atstkind_t0ype(atstype_double) arg0, atstkind_t0ype(atstype_double) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp24$2, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp25$2, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_ggte_val:
ATSINSmove(tmp25$2, atspre_g0float_compare_double(arg0, arg1)) ;
ATSINSmove(tmp24$2, ATSLIB_056$prelude_gte_g0int_int$8$2(tmp25$2, ATSPMVi0nt(0))) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp24$2) ;
} /* end of [ATSLIB_056$prelude_ggte_val$5$2] */

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/integer.dats: 27564(line=740, offs=15) -- 27605(line=740, offs=56)
*/
/*
local: 
global: gte_g0int_int$8$2(level=3)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = tk(3149)
tmparg = S2Evar(tk(3149))
tmpsub = Some(tk(3149) -> S2Eextkind(atstype_int))
*/
atstkind_t0ype(atstype_bool)
ATSLIB_056$prelude_gte_g0int_int$8$2(atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp28$2, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp29$2, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gte_g0int_int:
ATSINSmove(tmp29$2, atspre_g0int2int_int_int(arg1)) ;
ATSINSmove(tmp28$2, atspre_g0int_gte_int(arg0, tmp29$2)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp28$2) ;
} /* end of [ATSLIB_056$prelude_gte_g0int_int$8$2] */

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gnumber.dats: 2078(line=78, offs=17) -- 2109(line=78, offs=48)
*/
/*
local: 
global: gnumint$22$2(level=2)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = 
tmparg = S2Eapp(S2Ecst(g0float_t0ype); S2Ecst(double_kind))
tmpsub = Some()
*/
atstkind_t0ype(atstype_double)
ATSLIB_056$prelude_gnumint$22$2(atstkind_t0ype(atstype_int) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp52$2, atstkind_t0ype(atstype_double)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gnumint:
ATSINSmove(tmp52$2, atspre_g0int2float_int_double(arg0)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp52$2) ;
} /* end of [ATSLIB_056$prelude_gnumint$22$2] */

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gnumber.dats: 2377(line=94, offs=18) -- 2391(line=94, offs=32)
*/
/*
local: 
global: gsub_val$28$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = 
tmparg = S2Eapp(S2Ecst(g0float_t0ype); S2Ecst(double_kind))
tmpsub = None()
*/
atstkind_t0ype(atstype_double)
ATSLIB_056$prelude_gsub_val$28(atstkind_t0ype(atstype_double) arg0, atstkind_t0ype(atstype_double) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret59, atstkind_t0ype(atstype_double)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gsub_val:
ATSINSmove(tmpret59, PMVtmpltcst(g0float_sub<S2Eextkind(atstype_double)>)(arg0, arg1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret59) ;
} /* end of [ATSLIB_056$prelude_gsub_val$28] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gnumber.dats: 2377(line=94, offs=18) -- 2391(line=94, offs=32)
*/
/*
local: 
global: gsub_val$28$1(level=2)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = 
tmparg = S2Eapp(S2Ecst(g0float_t0ype); S2Ecst(double_kind))
tmpsub = Some()
*/
atstkind_t0ype(atstype_double)
ATSLIB_056$prelude_gsub_val$28$1(atstkind_t0ype(atstype_double) arg0, atstkind_t0ype(atstype_double) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp59$1, atstkind_t0ype(atstype_double)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gsub_val:
ATSINSmove(tmp59$1, atspre_g0float_sub_double(arg0, arg1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp59$1) ;
} /* end of [ATSLIB_056$prelude_gsub_val$28$1] */

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gnumber.dats: 2078(line=78, offs=17) -- 2109(line=78, offs=48)
*/
/*
local: 
global: gnumint$22$3(level=2)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = 
tmparg = S2Eapp(S2Ecst(g0float_t0ype); S2Ecst(double_kind))
tmpsub = Some()
*/
atstkind_t0ype(atstype_double)
ATSLIB_056$prelude_gnumint$22$3(atstkind_t0ype(atstype_int) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp52$3, atstkind_t0ype(atstype_double)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gnumint:
ATSINSmove(tmp52$3, atspre_g0int2float_int_double(arg0)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp52$3) ;
} /* end of [ATSLIB_056$prelude_gnumint$22$3] */

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gnumber.dats: 2377(line=94, offs=18) -- 2391(line=94, offs=32)
*/
/*
local: 
global: gsub_val$28$2(level=2)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = 
tmparg = S2Eapp(S2Ecst(g0float_t0ype); S2Ecst(double_kind))
tmpsub = Some()
*/
atstkind_t0ype(atstype_double)
ATSLIB_056$prelude_gsub_val$28$2(atstkind_t0ype(atstype_double) arg0, atstkind_t0ype(atstype_double) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp59$2, atstkind_t0ype(atstype_double)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gsub_val:
ATSINSmove(tmp59$2, atspre_g0float_sub_double(arg0, arg1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp59$2) ;
} /* end of [ATSLIB_056$prelude_gsub_val$28$2] */

#if(0)
/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gnumber.dats: 2335(line=92, offs=18) -- 2349(line=92, offs=32)
*/
/*
local: 
global: gadd_val$33$0(level=0)
local: 
global: 
*/
ATSglobaldec()
/*
imparg = 
tmparg = S2Eapp(S2Ecst(g0float_t0ype); S2Ecst(double_kind))
tmpsub = None()
*/
atstkind_t0ype(atstype_double)
ATSLIB_056$prelude_gadd_val$33(atstkind_t0ype(atstype_double) arg0, atstkind_t0ype(atstype_double) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmpret63, atstkind_t0ype(atstype_double)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gadd_val:
ATSINSmove(tmpret63, PMVtmpltcst(g0float_add<S2Eextkind(atstype_double)>)(arg0, arg1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmpret63) ;
} /* end of [ATSLIB_056$prelude_gadd_val$33] */
#endif // end of [TEMPLATE]

/*
/home/grad2/aren/workspace/Postiats/ATS-Postiats/prelude/DATS/gnumber.dats: 2335(line=92, offs=18) -- 2349(line=92, offs=32)
*/
/*
local: 
global: gadd_val$33$1(level=2)
local: 
global: 
*/
ATSstaticdec()
/*
imparg = 
tmparg = S2Eapp(S2Ecst(g0float_t0ype); S2Ecst(double_kind))
tmpsub = Some()
*/
atstkind_t0ype(atstype_double)
ATSLIB_056$prelude_gadd_val$33$1(atstkind_t0ype(atstype_double) arg0, atstkind_t0ype(atstype_double) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp63$1, atstkind_t0ype(atstype_double)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gadd_val:
ATSINSmove(tmp63$1, atspre_g0float_add_double(arg0, arg1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp63$1) ;
} /* end of [ATSLIB_056$prelude_gadd_val$33$1] */

/*
/home/grad2/aren/workspace/Postiats/projects/org.ats-lang.postiats.jats/test/gfib.dats: 803(line=50, offs=7) -- 920(line=56, offs=2)
*/
/*
local: 
global: mainats_void_0$1$0(level=0)
local: 
global: 
*/
ATSglobaldec()
atsvoid_t0ype
mainats_void_0()
{
/* tmpvardeclst(beg) */
ATStmpdec_void(tmpret9, atsvoid_t0ype) ;
ATStmpdec_void(tmp10, atsvoid_t0ype) ;
ATStmpdec_void(tmp11, atsvoid_t0ype) ;
ATStmpdec(tmp12, atstkind_t0ype(atstype_int)) ;
ATStmpdec_void(tmp39, atsvoid_t0ype) ;
ATStmpdec_void(tmp40, atsvoid_t0ype) ;
ATStmpdec_void(tmp41, atsvoid_t0ype) ;
ATStmpdec(tmp42, atstkind_t0ype(atstype_double)) ;
ATStmpdec_void(tmp65, atsvoid_t0ype) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_main_void_0:
/*
letpush(beg)
*/
ATSINSmove_void(tmp10, atspre_print_string(ATSPMVstring("fib(20) = "))) ;
ATSINSmove(tmp12, _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$1(ATSPMVi0nt(20))) ;
ATSINSmove_void(tmp11, atspre_print_int(tmp12)) ;
ATSINSmove_void(tmp39, atspre_print_newline()) ;
ATSINSmove_void(tmp40, atspre_print_string(ATSPMVstring("fib(20) = "))) ;
ATSINSmove(tmp42, _057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats_gfib$0$2(ATSPMVf0loat(20.0))) ;
ATSINSmove_void(tmp41, atspre_print_double(tmp42)) ;
ATSINSmove_void(tmp65, atspre_print_newline()) ;
/*
letpush(end)
*/

ATSINSmove_void(tmpret9, ATSempty()) ;
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn_void(tmpret9) ;
} /* end of [mainats_void_0] */


/*
** for initialization(dynloading)
*/
atsvoid_t0ype
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats__dynload()
{
ATSdynload0(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats__dynloadflag
) ;
ATSif(
ATSCKiseqz(
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats__dynloadflag
)
) ATSthen() {
ATSdynloadset(_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats__dynloadflag) ;
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
_057$home_057$grad2_057$aren_057$workspace_057$Postiats_057$projects_057$org_056$ats_055$lang_056$postiats_056$jats_057$test_057$gfib_056$dats__dynload() ;
ATSmainats_void_0(err) ;
return (err) ;
} /* end of [main] */
