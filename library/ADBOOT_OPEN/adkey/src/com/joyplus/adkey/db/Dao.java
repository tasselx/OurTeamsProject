package com.joyplus.adkey.db;



import java.util.ArrayList;
import java.util.List;

import com.joyplus.adkey.data.ScreenSaverInfo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * 一个业务类
 */
public class Dao {
	private static Dao dao = null;
	private Context context;

	private Dao(Context context) {
		this.context = context;
	}

	public static Dao getInstance(Context context) {
		if (dao == null) {
			dao = new Dao(context);
		}
		return dao;
	}

	public SQLiteDatabase getConnection() {
		SQLiteDatabase sqliteDatabase = null;
		try {
			sqliteDatabase = new DBHelper(context).getReadableDatabase();
		} catch (Exception e) {
		}
		return sqliteDatabase;
	}

	/**
	 * 查看数据库中是否有数据
	 */
	public synchronized boolean isHasInfors(String prod_id) {
		SQLiteDatabase database = getConnection();
		int count = -1;
		Cursor cursor = null;
		try {
			String sql = "select count(*)  from screen_info where publishid=?";
			cursor = database.rawQuery(sql, new String[] { prod_id});
			if (cursor.moveToFirst()) {
				count = cursor.getInt(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
			if (null != cursor) {
				cursor.close();
			}
		}
		return count == 0;
	}

	
	/*
	 * 保存缓存记录
	 */
	public synchronized void saveInfos(List<ScreenSaverInfo> infos) {
		SQLiteDatabase database = getConnection();
		try {
			for (ScreenSaverInfo info : infos) {
				String sql = "insert into screen_info(compeleteSize,fileSize, prod_id,my_index,url,urlposter,my_name,download_state,file_path) values (?,?,?,?,?,?,?,?,?)";
//				Object[] bindArgs = { info.getCompeleteSize(),
//						info.getFileSize(), info.getProd_id(), info.getMy_index(),
//						info.getUrl(), info.getUrlposter(), info.getMy_name(),
//						info.getDownload_state(),info.getFilePath()};
//				database.execSQL(sql, bindArgs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
		}
	}
	
	/*
	 * 插入一条记录
	 */
	public synchronized void InsertOneInfo(ScreenSaverInfo info) {
		SQLiteDatabase database = getConnection();
		try {
			String sql = "insert into download_info(compeleteSize,fileSize, prod_id,my_index,url,urlposter,my_name,download_state,file_path) values (?,?,?,?,?,?,?,?,?)";
//			Object[] bindArgs = { info.getCompeleteSize(), info.getFileSize(),
//					info.getProd_id(), info.getMy_index(), info.getUrl(),
//					info.getUrlposter(), info.getMy_name(), info.getDownload_state() ,info.getFilePath()};
//			database.execSQL(sql, bindArgs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
		}
	}
	
	/*
	 * 获取某一个记录,返回一个DownloadInfo list类型
	 */
	public synchronized List<ScreenSaverInfo> getInfos(String prod_id,
			String my_index) {
		List<ScreenSaverInfo> list = new ArrayList<ScreenSaverInfo>();
		SQLiteDatabase database = getConnection();
		Cursor cursor = null;
		try {
			String sql = "select compeleteSize,fileSize, prod_id,my_index,url,urlposter,my_name,download_state,file_path from download_info where prod_id=? and my_index=?";
			cursor = database.rawQuery(sql, new String[] { prod_id, my_index });
			while (cursor.moveToNext()) {
//				ScreenSaverInfo info = new ScreenSaverInfo(cursor.getInt(0),
//						cursor.getInt(1), cursor.getString(2),
//						cursor.getString(3), cursor.getString(4),
//						cursor.getString(5), cursor.getString(6),
//						cursor.getString(7), cursor.getString(8));
//				list.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
			if (null != cursor) {
				cursor.close();
			}
		}
		return list;
	}

	public synchronized ScreenSaverInfo getOneInfo(String prod_id, String my_index) {
		ScreenSaverInfo info = null;
		SQLiteDatabase database = getConnection();
		Cursor cursor = null;
		try {
			String sql = "select compeleteSize,fileSize, prod_id,my_index,url,urlposter,my_name,download_state,file_path from download_info where prod_id=? and my_index=?";
			cursor = database.rawQuery(sql, new String[] { prod_id, my_index });
			while (cursor.moveToNext()) {
//				info = new ScreenSaverInfo(cursor.getInt(0), cursor.getInt(1),
//						cursor.getString(2), cursor.getString(3),
//						cursor.getString(4), cursor.getString(5),
//						cursor.getString(6), cursor.getString(7),
//						cursor.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
			if (null != cursor) {
				cursor.close();
			}
		}
		return info;
	}
	
	/*
	 * 获取某一个状态的某一条记录
	 */
	public synchronized ScreenSaverInfo getOneStateInfo(String download_state) {
		ScreenSaverInfo info = null;
		SQLiteDatabase database = getConnection();
		Cursor cursor = null;
		try {
			String sql = "select compeleteSize,fileSize, prod_id,my_index,url,urlposter,my_name,download_state,file_path from download_info where download_state=?";
			cursor = database.rawQuery(sql, new String[] { download_state });
			while (cursor.moveToNext()) {
//				info = new ScreenSaverInfo(cursor.getInt(0), cursor.getInt(1),
//						cursor.getString(2), cursor.getString(3),
//						cursor.getString(4), cursor.getString(5),
//						cursor.getString(6), cursor.getString(7),
//						cursor.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
			if (null != cursor) {
				cursor.close();
			}
		}
		return info;
	}

	/*
	 * 返回数据库中所有的数据
	 */
	public synchronized List<ScreenSaverInfo> getDownloadInfos() {
		List<ScreenSaverInfo> list = new ArrayList<ScreenSaverInfo>();
		SQLiteDatabase database = getConnection();
		Cursor cursor = null;
		try {
			String sql = "select compeleteSize,fileSize, prod_id,my_index,url,urlposter,my_name,download_state,file_path from download_info";
			cursor = database.rawQuery(sql, null);
			while (cursor.moveToNext()) {
//				ScreenSaverInfo info = new ScreenSaverInfo(cursor.getInt(0),
//						cursor.getInt(1), cursor.getString(2),
//						cursor.getString(3), cursor.getString(4),
//						cursor.getString(5), cursor.getString(6),
//						cursor.getString(7), cursor.getString(8));
//				list.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
			if (null != cursor) {
				cursor.close();
			}
		}
		return list;
	}

	// 根据prod_id进行分组
	public synchronized List<ScreenSaverInfo> getDownloadInfosGroup() {
		List<ScreenSaverInfo> list = new ArrayList<ScreenSaverInfo>();
		SQLiteDatabase database = getConnection();
		Cursor cursor = null;
		try {
			String sql = "select compeleteSize,fileSize, prod_id,my_index,url,urlposter,my_name,download_state,file_path from download_info group by prod_id";
			cursor = database.rawQuery(sql, null);
			while (cursor.moveToNext()) {
//				ScreenSaverInfo info = new ScreenSaverInfo(cursor.getInt(0),
//						cursor.getInt(1), cursor.getString(2),
//						cursor.getString(3), cursor.getString(4),
//						cursor.getString(5), cursor.getString(6),
//						cursor.getString(7), cursor.getString(8));
//				list.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
			if (null != cursor) {
				cursor.close();
			}
		}
		return list;
	}

	// 获取某一个prod_id的所有数据,通常用于电视剧和节目
	public synchronized List<ScreenSaverInfo> getInfosOfProd_id(String prod_id) {
		List<ScreenSaverInfo> list = new ArrayList<ScreenSaverInfo>();
		SQLiteDatabase database = getConnection();
		Cursor cursor = null;
		try {
			String sql = "select compeleteSize,fileSize, prod_id,my_index,url,urlposter,my_name,download_state,file_path from download_info where prod_id=? order by cast(my_index as int)";
			cursor = database.rawQuery(sql, new String[] { prod_id });
			while (cursor.moveToNext()) {
//				ScreenSaverInfo info = new ScreenSaverInfo(cursor.getInt(0),
//						cursor.getInt(1), cursor.getString(2),
//						cursor.getString(3), cursor.getString(4),
//						cursor.getString(5), cursor.getString(6),
//						cursor.getString(7), cursor.getString(8));
//				list.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
			if (null != cursor) {
				cursor.close();
			}
		}
		return list;
	}

	/*
	 * 更新某一个下载记录下载了多少
	 */
	public synchronized void updataInfos(int compeleteSize, String prod_id,
			String my_index) {
		SQLiteDatabase database = getConnection();
		try {
			String sql = "update download_info set compeleteSize=? where prod_id=? and my_index=?";
			Object[] bindArgs = { compeleteSize, prod_id, my_index };
			database.execSQL(sql, bindArgs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
		}
	}
	
	/*
	 * 更新某一条记录
	 */
	public synchronized void updataInfos(ScreenSaverInfo info) {
		SQLiteDatabase database = getConnection();
		try {
			String sql = "update download_info set fileSize=? where prod_id=? and my_index=?";
//			Object[] bindArgs = { info.getFileSize(), info.getProd_id(), info.getMy_index() };
//			database.execSQL(sql, bindArgs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
		}
	}
	/*
	 * 更新某一条下载记录的状态
	 */
	public synchronized void updataInfoState(String download_state,
			String prod_id, String my_index) {
		SQLiteDatabase database = getConnection();
		try {
			String sql = "update download_info set download_state=? where prod_id=? and my_index=?";
			Object[] bindArgs = { download_state, prod_id, my_index };
			database.execSQL(sql, bindArgs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
		}
	}
	/*
	 * 下载完成后更新存放路径
	 */
	public synchronized void updataInfofilePath(String localfile,
			String prod_id, String my_index) {
		SQLiteDatabase database = getConnection();
		try {
			String sql = "update download_info set localfile=? where prod_id=? and my_index=?";
			Object[] bindArgs = { localfile, prod_id, my_index };
			database.execSQL(sql, bindArgs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
		}
	}

	/*
	 * 删除某一个记录
	 */
	public synchronized void delete(String prod_id, String my_index) {
		SQLiteDatabase database = getConnection();
		try {
			database.delete("download_info", "prod_id=? and my_index=?",
					new String[] { prod_id, my_index });
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
		}
	}
	
	/*
	 * 删除某个非电影的所有记录
	 */
	public synchronized void delete(String prod_id) {
		SQLiteDatabase database = getConnection();
		try {
			database.delete("download_info", "prod_id=?",
					new String[] { prod_id});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != database) {
				database.close();
			}
		}
	}
}