
(* ****** ****** *)
//
#include
"share/atspre_staload.hats"
//
(* ****** ****** *)

#define
ATS_PACKNAME "atspkgreloc_test"

(* ****** ****** *)
//
// #define
// CONATSCONTRIB "https://raw.githubusercontent.com/alex-ren/org.ats-lang.postiats.jats/master/utfpl/src/jats/utfpl/stfpl/test"
//

#define CONATSCONTRIB "http://cs-people.bu.edu/aren/tmp"
(* ****** ****** *)
//
require
"{$CONATSCONTRIB}/conats.sats"
//
(* ****** ****** *)
//
staload "{$CONATSCONTRIB}/conats.sats"
// staload T = "{$PATSCONTRIB}/libats-hwxi/intinf/SATS/intinf_t.sats"
// staload _ = "{$PATSCONTRIB}/libats-hwxi/intinf/DATS/intinf_t.dats"
// staload _ = "{$PATSCONTRIB}/libats-hwxi/intinf/DATS/intinf_vt.dats"
//
(* ****** ****** *)

implement
main0 () = () where
{
//
val N = 100
val () = println! ("Helloworld!")
val x = sys_list_nil ()
//
} (* end of [main0] *)

