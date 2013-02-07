/*
**
** The C code is generated by ATS/Postiats
** The compilation time is: 2013-1-24: 22h:17m
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
/*
typedefs-for-tyrecs-and-tysums(end)
*/
ATSstaticdec()
atstkind_t0ype(atstype_int)
f91_0 (atstkind_t0ype(atstype_int) arg0) ;

#if(0)
ATSglobaldec()
atstkind_t0ype(atstype_bool)
_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g0int_int$1 (atstkind_t0ype(postiats_undef(HSEtyvar(tk(2752)))) arg0, atstkind_t0ype(atstype_int) arg1) ;
#endif // end of [TEMPLATE]

ATSstaticdec()
atstkind_t0ype(atstype_bool)
_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g0int_int$1$1 (atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1) ;

ATSglobaldec()
atstkind_t0ype(atstype_int)
mainats () ;

ATSstaticdec()
atstkind_t0ype(atstype_int)
f91_0 (atstkind_t0ype(atstype_int) arg0)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp0, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp1, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp6, atstkind_t0ype(atstype_int)) ;
ATStmpdec(tmp7, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_f91_0:
ATSINSmove(tmp1, _057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g0int_int$1$1(arg0, ATSPMVi0nt(100))) ;
ATSif(tmp1) ATSthen() {
ATSINSmove(tmp0, atspre_g0int_sub_int(arg0, ATSPMVi0nt(10))) ;
} ATSelse() {
ATSINSmove(tmp7, atspre_g0int_add_int(arg0, ATSPMVi0nt(11))) ;
ATSINSmove(tmp6, f91_0(tmp7)) ;
ATSINSmove(tmp0, f91_0(tmp6)) ;
} /* end of [if] */
/* funbodyinstrlst(end) */
ATSreturn(tmp0) ;
} /* end of [f91_0] */
#if(0)
ATSglobaldec()
/*
imparg = tk(2752)
tmparg = S2Evar(tk(2752))
tmpsub = None()
*/
atstkind_t0ype(atstype_bool)
_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g0int_int$1 (atstkind_t0ype(postiats_undef(HSEtyvar(tk(2752)))) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp2, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp3, atstkind_t0ype(postiats_undef(HSEtyvar(tk(2752))))) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gt_g0int_int:
ATSINSmove(tmp3, PMVtmpltcst(g0int2int<S2EVar(375->S2Etkname(atstype_int)), S2EVar(376->S2Evar(tk(2752)))>)(arg1)) ;
ATSINSmove(tmp2, PMVtmpltcst(g0int_gt<S2Evar(tk(2752))>)(arg0, tmp3)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp2) ;
} /* end of [_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g0int_int$1] */
#endif // end of [TEMPLATE]
ATSstaticdec()
/*
imparg = tk(2752)
tmparg = S2Evar(tk(2752))
tmpsub = Some(tk(2752) -> S2Etkname(atstype_int))
*/
atstkind_t0ype(atstype_bool)
_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g0int_int$1$1 (atstkind_t0ype(atstype_int) arg0, atstkind_t0ype(atstype_int) arg1)
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp2$1, atstkind_t0ype(atstype_bool)) ;
ATStmpdec(tmp3$1, atstkind_t0ype(atstype_int)) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_gt_g0int_int:
ATSINSmove(tmp3$1, atspre_g0int2int_int_int(arg1)) ;
ATSINSmove(tmp2$1, atspre_g0int_gt_int(arg0, tmp3$1)) ;
/* funbodyinstrlst(end) */
ATSreturn(tmp2$1) ;
} /* end of [_057$home_057$hwxi_057$research_057$Postiats_057$git_057$prelude_057$SATS_057$integer_056$sats__gt_g0int_int$1$1] */
ATSglobaldec()
atstkind_t0ype(atstype_int)
mainats ()
{
/* tmpvardeclst(beg) */
ATStmpdec(tmp8, atstkind_t0ype(atstype_int)) ;
ATStmpdec_void(tmp9, atsvoid_t0ype) ;
ATStmpdec_void(tmp10, atsvoid_t0ype) ;
ATStmpdec(tmp11, atstkind_t0ype(atstype_int)) ;
ATStmpdec_void(tmp12, atsvoid_t0ype) ;
/* tmpvardeclst(end) */
/* funbodyinstrlst(beg) */
__patsflab_main_void:
/*
letpush(beg)
*/
ATSINSmove_void(tmp9, atspre_print_string(ATSPMVstring("f91(10) = "))) ;
ATSINSmove(tmp11, f91_0(ATSPMVi0nt(10))) ;
ATSINSmove_void(tmp10, atspre_print_int(tmp11)) ;
ATSINSmove_void(tmp12, atspre_print_newline()) ;
/*
letpush(end)
*/

ATSINSmove(tmp8, ATSPMVi0nt(0)) ;
/*
INSletpop()
*/
/* funbodyinstrlst(end) */
ATSreturn(tmp8) ;
} /* end of [mainats] */
/*
** declaration initialization
*/