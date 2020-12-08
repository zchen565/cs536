.text
main:
__start:
	sw    $ra, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	sw    $fp, 0($sp)	#PUSH
	subu  $sp, $sp, 4
	subu  $sp, $sp, 0
	addu  $fp, $sp, 8

.data
.L0: .asciiz "Hello world!"
.text
	la    $a0, .L0
	li    $v0, 4
	syscall

_main_exit:
	li    $v0, 10
	syscall

