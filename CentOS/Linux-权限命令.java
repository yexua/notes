————————————————————————————
 Linux-权限管理				|
————————————————————————————
	# 在Linux中,touch创建一个新的文件.是默认没有执行权限的(默认新建的文件是没有可执行权限的)	
	# 删除一个文件的权限,并不是看你对文件本身是否有写权限!而是要看'该文件所在的目录,你是否有写权限'

————————————————————————————
 Linux-修改文件权限			|
————————————————————————————
	# 只有文件啊所属和root才可以修改文件权限

	chmod 
		# 修改指定文件的权限
		# 支持同时操作,用逗号隔开
		# 选项
			-u		//文件所有者
			-g		//文件所属组
			-o		//其他人
			-a		//所有人
			-R		//递归修改,可以针对目录进行授权
			+ [rwx]	//添加权限
			- [rwx]	//删除权限
			= [rwx]	//不管你有没有,反正就是给这个权限

		
		# chmod u+w,o-r [文件]
			* 为文件所有者添加写权限,并且删除所有人的运行权限
		# chmod o=rwx [文件]
			* 为所有人赋所有权限
				
	# 数字修改法
		r ---- 4			//2的二次方
		w ---- 2			//2的1次方
		x ---- 1			//2的0次方
	
		rwx rw- r--			
		7	6	4	
				
		1,2,3,4,5,6,7
		r-x -wx- -w-
		chmod 640 [文件名]
	
	# rwx 权限详解
		----------------------------------------------------------------------
		权限	名字		对于文件			对于目录
		----------------------------------------------------------------------
		r		读权限		可以查看内容		可以列出目录中的所有文件
		----------------------------------------------------------------------
		w		写权限		可以修改文件内容	可以在目录中创建文件,删除文件
		----------------------------------------------------------------------
		x		执行权限	可以执行文件		可以进入目录
		----------------------------------------------------------------------
	
	# 删除一个文件的权限,并不是看你对文件本身是否有写权限!而是要看'该文件所在的目录,你是否有写权限'

————————————————————————————
 Linux-修改文件所有			|
————————————————————————————
	# 只有 root 才能修改文件的所有者/所属组
	
	chown [用户] [文件或者目录]
		# 修改指定文件的所有者
	chgrp [用户组] [文件或者目录]
		# 修改指定文件的所属组
	
	# 可以一次性的修改文件所属组和所属者 
		chown [所属者]:[所属组] [文件]
		* 注意中间是个冒号

————————————————————————————
 Linux-查看新建文件缺省权限|
————————————————————————————
	# 显示新建文件的默认权限
		umask -S
		* u=rwx,g=rx,o=rx
	
	# 修改新建文件的缺省权限
		umask [数值]
			* 这个有印象就好... ..用到再去查

	# 在Linux中,touch创建一个新的文件.是默认没有执行权限的(默认新建的文件是没有可执
	
		
