package com.wuzhupc.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Date;

import com.wuzhupc.config.Constants;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.Formatter;
import android.util.Log;

/**
 * �ļ��������
 * 
 * @author wuzhu 20110603
 * 
 */
public class FileUtil
{

	private static final String TAG = FileUtil.class.getSimpleName();

	/**
	 * ��ȡʵ�ʵ�ͼƬ·��
	 * 
	 * @param contentUri
	 *            ��Uri·��
	 * @param activity
	 * @return
	 */
	public static String getRealPathFromURI(Uri contentUri, Activity activity)
	{
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = activity.managedQuery(contentUri, proj, null, null, null);
		if (cursor == null)
			return "";
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		if (cursor.moveToFirst())
			return cursor.getString(column_index);
		else
			return "";
	}

	/**
	 * �жϴ洢���Ƿ����
	 */
	public static Boolean hasSDCard()
	{
		return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}

	/**
	 * ��ȡ�洢��·����·����/ ���û�д洢�������ؿ��ַ���
	 */
	public static String getSDCardPath()
	{
		if (hasSDCard())
			return Environment.getExternalStorageDirectory() + File.separator;
		else
			return "";
	}

	/**
	 * ��ʱ�洢��Ƭ·��
	 * 
	 * @return
	 */
	public static String getTempPhotoPath()
	{
		return Constants.CSTR_DATASTOREDIR + "images" + File.separator + "tmpphoto.jpg";
	}

	/**
	 * ��ʱ�洢��Ƶ·��
	 * 
	 * @return
	 */
	public static String getTempVideoPath()
	{
		return Constants.CSTR_DATASTOREDIR + "images" + File.separator
				+ TimeUtil.dateToString(new Date(), "yyyyMMddHHmmss") + ".3gp";
	}

	/**
	 * ���ļ�
	 * 
	 **/
	public static void OpenFile(File f, Context c)
	{
		if (!f.exists() || c == null)
			return;

		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);

		/* ����getMIMEType()��ȡ��MimeType */
		String type = getMIMEType(f);
		/* ����intent��file��MimeType */
		intent.setDataAndType(Uri.fromFile(f), type);
		c.startActivity(intent);
	}

	/**
	 * �����ļ���׺����ö�Ӧ��MIME���͡�
	 * 
	 * @param file
	 */
	public static String getMIMEType(File file)
	{
		String type = "*/*";
		String fName = file.getName();
		// ��ȡ��׺��ǰ�ķָ���"."��fName�е�λ�á�
		int dotIndex = fName.lastIndexOf(".");
		if (dotIndex < 0)
		{
			return type;
		}
		/* ��ȡ�ļ��ĺ�׺�� */
		String end = fName.substring(dotIndex, fName.length()).toLowerCase();
		if (end == "")
			return type;
		// ��MIME���ļ����͵�ƥ������ҵ���Ӧ��MIME���͡�
		for (int i = 0; i < MIME_MapTable.length; i++)
		{
			if (end.equals(MIME_MapTable[i][0]))
				type = MIME_MapTable[i][1];
		}
		return type;
	}

	/**
	 * ��ȡ�ļ���׺��
	 * 
	 * @param filename
	 *            �ļ�����
	 * @return
	 */
	public static String getFileExtension(String filename)
	{
		if (StringUtil.isEmpty(filename))
			return "";
		int dotIndex = filename.lastIndexOf(".");
		if (dotIndex < 0)
			return "";
		/* ��ȡ�ļ��ĺ�׺�� */
		return filename.substring(dotIndex + 1, filename.length()).toLowerCase();
	}

	/**
	 * ����һ��MIME�������ļ���׺����ƥ���
	 */
	public static final String[][] MIME_MapTable = {
			// {��׺���� MIME����}
			{ ".3gp", "video/3gpp" }, { ".apk", "application/vnd.android.package-archive" },
			{ ".asf", "video/x-ms-asf" }, { ".avi", "video/x-msvideo" }, { ".bin", "application/octet-stream" },
			{ ".bmp", "image/bmp" }, { ".c", "text/plain" }, { ".class", "application/octet-stream" },
			{ ".conf", "text/plain" }, { ".cpp", "text/plain" }, { ".doc", "application/msword" },
			{ ".exe", "application/octet-stream" }, { ".gif", "image/gif" }, { ".gtar", "application/x-gtar" },
			{ ".gz", "application/x-gzip" }, { ".h", "text/plain" }, { ".htm", "text/html" }, { ".html", "text/html" },
			{ ".jar", "application/java-archive" }, { ".java", "text/plain" }, { ".jpeg", "image/jpeg" },
			{ ".jpg", "image/jpeg" }, { ".js", "application/x-javascript" }, { ".log", "text/plain" },
			{ ".m3u", "audio/x-mpegurl" }, { ".m4a", "audio/mp4a-latm" }, { ".m4b", "audio/mp4a-latm" },
			{ ".m4p", "audio/mp4a-latm" }, { ".m4u", "video/vnd.mpegurl" }, { ".m4v", "video/x-m4v" },
			{ ".mov", "video/quicktime" }, { ".mp2", "audio/x-mpeg" }, { ".mp3", "audio/x-mpeg" },
			{ ".mp4", "video/mp4" }, { ".mpc", "application/vnd.mpohun.certificate" }, { ".mpe", "video/mpeg" },
			{ ".mpeg", "video/mpeg" }, { ".mpg", "video/mpeg" }, { ".mpg4", "video/mp4" }, { ".mpga", "audio/mpeg" },
			{ ".msg", "application/vnd.ms-outlook" }, { ".ogg", "audio/ogg" }, { ".pdf", "application/pdf" },
			{ ".png", "image/png" }, { ".pps", "application/vnd.ms-powerpoint" },
			{ ".ppt", "application/vnd.ms-powerpoint" }, { ".prop", "text/plain" },
			{ ".rar", "application/x-rar-compressed" }, { ".rc", "text/plain" }, { ".rmvb", "audio/x-pn-realaudio" },
			{ ".rtf", "application/rtf" }, { ".sh", "text/plain" }, { ".tar", "application/x-tar" },
			{ ".tgz", "application/x-compressed" }, { ".txt", "text/plain" }, { ".wav", "audio/x-wav" },
			{ ".wma", "audio/x-ms-wma" }, { ".wmv", "audio/x-ms-wmv" }, { ".wps", "application/vnd.ms-works" },
			// {".xml", "text/xml"},
			{ ".xml", "text/plain" }, { ".z", "application/x-compress" }, { ".zip", "application/zip" }, { "", "*/*" } };

	/**
	 * ��ȡ�ļ�
	 * 
	 * @param file
	 *            ����ļ�·��
	 * @return
	 */
	public static byte[] ReadFileToBytes(String file)
	{
		FileInputStream fIn = null;

		byte[] buffer = null;
		try
		{
			File f = new File(file);
			fIn = new FileInputStream(f);
			buffer = new byte[fIn.available()];
			fIn.read(buffer);
		} catch (Exception e)
		{
			Log.e(TAG, e.getMessage());
		} finally
		{
			try
			{
				if (fIn != null)
					fIn.close();
			} catch (IOException e)
			{
				Log.e(TAG, e.getMessage());
			}
		}
		return buffer;
	}

	/**
	 * ��ȡ�ļ�
	 * 
	 * @param file
	 *            ����ļ�·��
	 * @return
	 */
	public static String ReadFile(String file)
	{
		return ReadFile(file, "UTF-8");
	}

	/**
	 * ��ȡ�ļ�
	 * 
	 * @param file
	 *            ����ļ�·��
	 * @param charset
	 *            �ַ���,Ĭ��UTF-8
	 * @return
	 */
	public static String ReadFile(String file, String charset)
	{
		File f = new File(file);
		if (!f.exists())
			return null;
		Charset cs;
		try
		{
			cs = Charset.forName(charset);
		} catch (Exception e)
		{
			cs = Charset.forName("UTF-8");
		}
		FileInputStream fIn = null;
		InputStreamReader isr = null;
		StringBuilder result = new StringBuilder();
		BufferedReader br = null;
		try
		{
			fIn = new FileInputStream(f);
			isr = new InputStreamReader(fIn, cs);
			br = new BufferedReader(isr);
			String temp;
			int line=0;
			while ((temp = br.readLine()) != null)
			{
				if(line!=0)
					result.append("\r\n");
				result.append(temp);
				line++;
			}
		} catch (Exception e)
		{
			Log.e(TAG, e.getMessage());
		} finally
		{
			try
			{
				if (br != null)
					br.close();
				if (isr != null)
					isr.close();
				if (fIn != null)
					fIn.close();
			} catch (IOException e)
			{
				Log.e(TAG, e.getMessage());
			}
		}
		return result.toString();
	}

	/**
	 * �����ļ�����
	 */
	public static void WriteFile(String file, byte[] data)
	{
		FileOutputStream fOut = null;
		try
		{
			File f = new File(file);
			fOut = new FileOutputStream(f, true);
			fOut.write(data);
		} catch (Exception e)
		{
			Log.e(TAG, e.getMessage());
		} finally
		{
			try
			{
				if (fOut != null)
					fOut.close();
			} catch (IOException e)
			{

				Log.e(TAG, e.getMessage());
			}
		}
	}

	/**
	 * д���ļ�����,���ԭ�ļ����ڣ�������ԭ�ļ�����(UTF-8)
	 * 
	 * @param file
	 *            ���ļ���
	 * @param data
	 *            ��Ҫд�������
	 */
	public static void WriteFile(String file, String data)
	{
		WriteFile(file, data, false);
	}

	/**
	 * �����ļ�����(UTF-8)
	 * 
	 * @param file
	 *            ���ļ���
	 * @param data
	 *            ��Ҫд�������
	 */
	public static void WriteFile(String file, String data, Boolean append)
	{
		FileOutputStream fOut = null;
		OutputStreamWriter osw = null;
		try
		{
			isExistFolderFromFile(file); // �ļ��д�������⣬�������򴴽�

			File f = new File(file);
			fOut = new FileOutputStream(f, append);
			osw = new OutputStreamWriter(fOut,Charset.forName("UTF-8"));
			osw.write(data);
			osw.flush();
		} catch (Exception e)
		{
			Log.e(TAG, e.toString());
		} finally
		{
			try
			{
				if (osw != null)
					osw.close();
				if (fOut != null)
					fOut.close();
			} catch (IOException e)
			{

				Log.e(TAG, e.getMessage());
			}
		}
	}

	/**
	 * ɾ���ļ�
	 * 
	 * @param filename
	 *            �ļ���
	 * @return
	 */
	public static boolean deleteFile(String filename)
	{
		File f = new File(filename);

		if (!f.exists())
		{
			return true;
		}

		boolean flag = f.delete();

		return flag;
	}

	/**
	 * ɾ���ļ���
	 * 
	 * @param filePathAndName
	 *            String �ļ���·�������� ��c:/fqf
	 * @param fileContent
	 *            String
	 * @return boolean
	 */

	public static Boolean delFolder(String folderPath)
	{
		try
		{
			delAllFile(folderPath); // ɾ����������������
			String filePath = folderPath;
			filePath = filePath.toString();
			// Log.d("delFolder", filePath.toString());
			File f = new File(filePath);
			if (f.exists())
				f.delete(); // ɾ�����ļ���
			return true;

		} catch (Exception e)
		{
			Log.e("delFolder", "ɾ���ļ��в�������:" + e.getMessage());
			return false;
		}
	}

	/**
	 * ɾ���ļ�������������ļ�
	 * 
	 * @param path
	 *            String �ļ���·�� �� c:/fqf
	 */
	public static boolean delAllFile(String path)
	{
		File file = new File(path);

		if (!file.exists())
		{
			Log.d("no_file", "nofile");
			return true;
		}
		if (!file.isDirectory())
		{
			Log.d("no_file", "File_Directory");
			return true;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++)
		{
			if (path.endsWith(File.separator))
			{
				temp = new File(path + tempList[i]);
				Log.d("FileUtil ", "Temp_Name" + temp.getName());

			} else
			{
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile())
			{
				temp.delete();
			}
			if (temp.isDirectory())
			{
				delAllFile(path + "/" + tempList[i]);// ��ɾ���ļ���������ļ�
				delFolder(path + "/" + tempList[i]);// ��ɾ�����ļ���
			}
		}
		return true;
	}

	/**
	 * inputstream ��Ϊ�ļ������ļ����ڵĻ��ᱻɾ��
	 * 
	 * @param inputStream
	 * @param destFile
	 * @return
	 */
	public static boolean copyToFile(InputStream inputStream, File destFile)
	{
		try
		{
			if (destFile.exists())
			{
				destFile.delete();
			} else
			{
				if (!isExistFolderFromFile(destFile.getPath()))
					return false;
			}
			OutputStream out = new FileOutputStream(destFile);
			try
			{
				byte[] buffer = new byte[4096];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) >= 0)
				{
					out.write(buffer, 0, bytesRead);
				}
			} finally
			{
				out.close();
			}
			return true;
		} catch (IOException e)
		{
			return false;
		}
	}

	/**
	 * �ж��ļ��Ƿ����
	 * 
	 * @param strfilename
	 * @return
	 */
	public static boolean isExistFile(String strfilename)
	{
		if (StringUtil.isEmpty(strfilename))
			return false;
		File f = new File(strfilename);
		if (f == null || !f.exists())
			return false;
		return true;
	}

	/**
	 * �ж��ļ����ļ����Ƿ����
	 * 
	 * @param strfilename
	 *            �ļ��������ļ���
	 * @return
	 */
	public static boolean isExistFolderFromFile(String strfilename)
	{
		if (StringUtil.isEmpty(strfilename))
			return false;
		int index = strfilename.lastIndexOf(File.separator);
		if (index <= 0)
			return false;
		String fdir = strfilename.substring(0, index);
		return isExistFolder(fdir);
	}

	/**
	 * �ж��Ƿ�����ļ���,��������᳥�Խ��д���
	 */
	public static boolean isExistFolder(String strFolder)
	{
		if (strFolder == null)
			return true;
		boolean bReturn = false;

		File f = new File(strFolder);
		if (!f.exists())
		{
			/* �����ļ��� */
			if (f.mkdirs())
			{
				bReturn = true;
			} else
			{
				bReturn = false;
			}
		} else
		{
			bReturn = true;
		}
		return bReturn;
	}

	/**
	 * ��ȡ�ļ���
	 * 
	 * @param fullfilename
	 *            ���ļ������·������URL��ַ
	 * @return �ļ���,�������·���ﲻ����/��\��ֱ�ӷ���fullfilename
	 */
	public static String getFileName(String fullfilename)
	{
		if (StringUtil.isEmpty(fullfilename))
			return "";
		int index = -1;
		if (fullfilename.contains(File.separator))
		{
			index = fullfilename.lastIndexOf(File.separator);
		} else
			index = fullfilename.lastIndexOf("\\");
		if (index == -1)
			return fullfilename;
		else if (index >= fullfilename.length() - 1)
			return "";
		return fullfilename.substring(index + 1);
	}

	/**
	 * �洢����
	 * 
	 * @param filename
	 *            �ļ��������������/·������洢���洢��Ŀ¼��
	 * @param filecontent
	 *            Ҫ�洢������
	 */
	public static void saveContent(String filename, String filecontent)
	{
		if (StringUtil.isEmpty(filename) || StringUtil.isEmpty(filecontent))
			return;
		String filefullpath;
		if (filename.contains(File.separator))
			filefullpath = filename;
		else
			filefullpath = getSDCardPath() + filename;
		WriteFile(filefullpath, filecontent, false);
	}

	/**
	 * ��ȡ�ļ�������
	 * 
	 * @param filename
	 *            �ļ��������������/·������洢���洢��Ŀ¼��
	 * @return
	 */
	public static String readContent(String filename)
	{
		if (StringUtil.isEmpty(filename))
			return "";
		String filefullpath;
		if (filename.contains(File.separator))
			filefullpath = filename;
		else
			filefullpath = getSDCardPath() + filename;
		return ReadFile(filefullpath);
	}

	/**
	 * ��ʽ����С
	 */
	public static String FormatFileSize(Context c, long size)
	{
		return Formatter.formatFileSize(c, size);
	}

	/**
	 * ��ȡ�ļ���������·��
	 * 
	 * @param urlPath
	 * @return
	 */
	public static String GetUpdateFileSaveFileName(String urlPath)
	{
		String fileName = FileUtil.getFileName(urlPath);

		return Constants.CSTR_DATASTOREDIR + fileName;
	}

	/**
	 * ��ȡͼƬ����ͼ ֻ��Android2.1���ϰ汾֧��
	 * 
	 * @param imgName
	 * @param kind
	 *            MediaStore.Images.Thumbnails.MICRO_KIND
	 * @return
	 */
	public static Bitmap loadImgThumbnail(Activity activity, String imgName, int kind)
	{
		Bitmap bitmap = null;

		String[] proj = { MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME };

		Cursor cursor = activity.managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, proj,
				MediaStore.Images.Media.DISPLAY_NAME + "='" + imgName + "'", null, null);

		if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst())
		{
			ContentResolver crThumb = activity.getContentResolver();
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 1;
			bitmap = MediaStore.Images.Thumbnails.getThumbnail(crThumb, cursor.getInt(0), kind, options);
		}
		return bitmap;
	}

	/**
	 * ��Assets�ж�ȡͼƬ
	 */
	@TargetApi(5)
	public static Bitmap getImageFromAssetsFile(Activity activity, String fileName)
	{
		Bitmap image = null;
		AssetManager am = activity.getResources().getAssets();
		try
		{
			InputStream is = am.open(fileName);
			image = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return image;
	}
	
	/**
	 * ��Assets�ж�ȡ�ļ�
	 */
	public static String readFileFromAssetsFile(Context context, String fileName)
	{
		StringBuilder result = new StringBuilder();
		AssetManager am = context.getResources().getAssets();
		try
		{
			InputStream is = am.open(fileName);
			Charset cs=Charset.forName("UTF-8");
			InputStreamReader isr = null;
			BufferedReader br = null;
			try
			{
				isr = new InputStreamReader(is, cs);
				br = new BufferedReader(isr);
				String temp;
				while ((temp = br.readLine()) != null)
				{
					result.append(temp);
					result.append("\r\n");
				}
			} catch (Exception e)
			{
				Log.e(TAG, e.getMessage());
			} finally
			{
				try
				{
					if (br != null)
						br.close();
					if (isr != null)
						isr.close();
				} catch (IOException e)
				{
					Log.e(TAG, e.getMessage());
				}
			}
			is.close();
		}
		catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
		return result.toString();
	}
}
