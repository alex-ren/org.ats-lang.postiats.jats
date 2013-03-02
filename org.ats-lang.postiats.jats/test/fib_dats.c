/*
**
** The C code is generated by ATS/Postiats
** The compilation time is: 2013-1-24: 22h:16m
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
#include "pats_ccomp_exception.h"
#include "pats_ccomp_memalloc.h"
#endif /* _ATS_CCOMP_HEADER_NONE */


/*
** include prelude cats files
*/
#ifndef _ATS_CCOMP_PRELUDE_NONE
//
#include "prelude/CATS/basics.cats"
#include "prelude/CATS/integer.cats"
#include "prelude/CATS/pointer.cats"
#include "prelude/CATS/bool.cats"
#include "prelude/CATS/char.cats"
#include "prelude/CATS/string.cats"
#include "prelude/CATS/float.cats"
//
#include "prelude/CATS/list.cats"
#include "prelude/CATS/option.cats"
#include "prelude/CATS/array.cats"
#include "prelude/CATS/arrayptr.cats"
#include "prelude/CATS/arrayref.cats"
#include "prelude/CATS/matrix.cats"
//
#endif /* _ATS_CCOMP_PRELUDE_NONE */

/*
typedefs-for-tyrecs-and-tysums(beg)
*/
typedef
struct {
atstkind_t0ype(atstype_int) atslab$0; 
atstkind_t0ype(atstype_int) atslab$1; 
} postiats_tyrec_0 ;
/*
typedefs-for-tyrecs-and-tysums(end)
*/
ATSstaticdec()
atstkind_t0ype(atstype_int)
fib_0 (atstkind_t0ype(atstype_int) arg0) ;

ATSstaticdec()
atsvoid_t0ype
loop_1 (atsrefarg1_type(postiats_tyrec_0) arg0, atstkind_t0ype(atstype_int) arg1) ;

#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_bool)
_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g0int_int$2 (atstkind_t0ype(postiats_undef(HSEtyvar(tk(2752)))) arg0, atstkind_t0ype(atstype_int) arg1) ;
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_bool)
_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g0int_int$2$1 (atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1) ;

ATSglobaldec()
atstkind_t0ype(atstype_int)
mainats () ;

ATSstaticdec()
atstkind_t0ype(atstype_int)
fib_0 (atstkind_t0ype(atstype_int) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp0, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp11, postiats_tyrec_0) ;
ATStmpdec_void(tmp12, atsvoid_t0ype) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_fib_0:
/*
letpush(beg)
*/
ATSINSstore_fltrec_ofs (tmp11, postiats_tyrec_0, atslab$0, ATSPMVi0nt(0)) ;
ATSINSstore_fltrec_ofs (tmp11, postiats_tyrec_0, atslab$1, ATSPMVi0nt(1)) ;
ATSINSmove_void(tmp12, loop_1(ATSPMVrefarg1(ATSPMVptrof(tmp11)), arg0)) ;
/*
letpush(end)
*/

ATSINSload(tmp0, ATSselfltrec(tmp11, postiats_tyrec_0, atslab$0)) ; 
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn(tmp0) ;
} /* end of [fib_0] */
ATSstaticdec()
atsvoid_t0ype
loop_1 (atsrefarg1_type(postiats_tyrec_0) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec_void(tmp1, atsvoid_t0ype) ;
ATStmpdec(tmp2, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp7, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp8, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp9, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp10, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_loop_1:
/*
letpush(beg)
*/
/*
letpush(end)
*/

ATSINSmove(tmp2, _057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g0int_int$2$1(arg1, ATSPMVi0nt(0))) ;
ATSif(tmp2) ATSthen() {
/*
letpush(beg)
*/
ATSINSload(tmp7, ATSselfltrec(ATSderef(arg0), postiats_tyrec_0, atslab$0)) ; 
ATSINSload(tmp8, ATSselfltrec(ATSderef(arg0), postiats_tyrec_0, atslab$1)) ; 
ATSINSstore(ATSselfltrec(ATSderef(arg0), postiats_tyrec_0, atslab$0), tmp8) ; 
ATSINSmove(tmp9, atspre_g0int_add_int(tmp7, tmp8)) ;
ATSINSstore(ATSselfltrec(ATSderef(arg0), postiats_tyrec_0, atslab$1), tmp9) ; 
/*
letpush(end)
*/

ATSINSmove(tmp10, atspre_g0int_sub_int(arg1, ATSPMVi0nt(1))) ;
ATSINSmove_void(tmp1, loop_1(ATSPMVrefarg1(arg0), tmp10)) ;
/*
INSletpop()
*/
} ATSelse() {
ATSINSmove_void(tmp1, ATSempty()) ;
} /* end of [if] */
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn_void(tmp1) ;
} /* end of [loop_1] */
#if(0)
ATSglobaldec()
/*
imparg = tk(2752)
tmparg = S2Evar(tk(2752))
tmpsub = None()
*/
atstkind_t0ype(atstype_bool)
_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g0int_int$2 (atstkind_t0ype(postiats_undef(HSEtyvar(tk(2752)))) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp3, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp4, atstkind_t0ype(postiats_undef(HSEtyvar(tk(2752))))) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gt_g0int_int:
ATSINSmove(tmp4, PMVtmpltcst(g0int2int<S2EVar(375->S2Etkname(atstype_int)), S2EVar(376->S2Evar(tk(2752)))>)(arg1)) ;
ATSINSmove(tmp3, PMVtmpltcst(g0int_gt<S2Evar(tk(2752))>)(arg0, tmp4)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp3) ;
} /* end of [_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g0int_int$2] */
#endif // end of [TEMPLATE]
ATSstaticdec()
/*
imparg = tk(2752)
tmparg = S2Evar(tk(2752))
tmpsub = Some(tk(2752) -> S2Etkname(atstype_int))
*/
atstkind_t0ype(atstype_bool)
_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g0int_int$2$1 (atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp3$1, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp4$1, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gt_g0int_int:
ATSINSmove(tmp4$1, atspre_g0int2int_int_int(arg1)) ;
ATSINSmove(tmp3$1, atspre_g0int_gt_int(arg0, tmp4$1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp3$1) ;
} /* end of [_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g0int_int$2$1] */
ATSglobaldec()
atstkind_t0ype(atstype_int)
mainats ()
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp13, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp14, atstkind_t0ype(atstype_int)) ;
ATStmpdec_void(tmp15, atsvoid_t0ype) ;
ATStmpdec_void(tmp16, atsvoid_t0ype) ;
ATStmpdec_void(tmp17, atsvoid_t0ype) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_main_void:
/*
letpush(beg)
*/
ATSINSmove(tmp14, fib_0(ATSPMVi0nt(5))) ;
ATSINSmove_void(tmp15, atspre_print_string(ATSPMVstring("ans = "))) ;
ATSINSmove_void(tmp16, atspre_print_int(tmp14)) ;
ATSINSmove_void(tmp17, atspre_print_newline()) ;
/*
letpush(end)
*/

ATSINSmove(tmp13, ATSPMVi0nt(0)) ;
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn(tmp13) ;
} /* end of [mainats] */
/*
** declaration initialization
*/
