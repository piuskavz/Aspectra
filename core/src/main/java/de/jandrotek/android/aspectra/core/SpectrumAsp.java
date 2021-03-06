/**
 * This file is part of Aspectra.
 *
 * Aspectra is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Aspectra is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Aspectra.  If not, see <http://www.gnu.org/licenses/lgpl.html>.
 *
 * Copyright Jan Debiec
 */
package de.jandrotek.android.aspectra.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * class for saving,reading Aspectra-Spectra files.
 * Aspectra files:
 * 		header + data
 * @author jan
 *
 * changes:
 * 2015.03.22   added normalizeToSize
 *              raw spectrum data will be saved in the size got from sensor
 *              To allow compare and multishow,
 *              the spectra must be resized (normalized) to size 1024 (first compromise)
 *
 */
public class SpectrumAsp extends SpectrumBase {
	private Header mHeader = null;
//    private int[] mValues;
    private static final String eSep = "|";

    public int getDataSize() {
    	int size;
    	if (mHeader != null)
    		size = mHeader.dataLength;
    	else
    		size = 0;
//        size = SPK_CHR_FILE_DEFAULT_SIZE;
		return size;
	}

    public int getDataType() {
        int type;
        if (mHeader != null)
            type = mHeader.dataType;
        else
            type = AspectraGlobals.eTypeUnkown;
        return type;
    }

    public void setDataSize(int dataSize) {
    	if (mHeader == null)
    		mHeader = new Header();
		mHeader.dataLength = dataSize;
	}

    public String getNotes(){
        if (mHeader != null)
            return mHeader.notes;
        else
            return "any";

    }
	public SpectrumAsp(){
        mFileName = "";
	}

	public SpectrumAsp(String fileName){
        mFileName = fileName;
	}

//	/**
//	 * create new ApsSpectrum from ChrSpectrum
//	 * @param chrSpectrum
//	 */
//	public SpectrumAsp(Spectrum chrSpectrum){
//
//	}

	public void setData(int[] data, int normalizeTo){
        setDataSize(data.length);
        if(normalizeTo == AspectraGlobals.eNormalize1024){
            //TODO normalize to 1024
        } else {
            mHeader.dataType = AspectraGlobals.eTypeInt32;
            mValues = data;
        }
	}

    public String toString(){
        StringBuilder buffer = new StringBuilder();
        buffer.append(mHeader.toString());
        if(mHeader.dataType == AspectraGlobals.eTypeInt32) {
            for (int i = 0; i < mHeader.dataLength; i++) {
                buffer.append(Integer.toString(mValues[i]));
                buffer.append("\n");
            }
        } else if (mHeader.dataType == AspectraGlobals.eTypeFloat32) {
            for (int i = 0; i < mHeader.dataLength; i++) {
                buffer.append(Float.toString(mValues[i]));
                buffer.append("\n");
            }
        }
        return buffer.toString();

    }

    /**
     * function to read Chroc *.spk files
     * @return size of read spectrum
     * @throws Exception
     */
    public int readValuesFromFile() {
//    public int readValuesFromFile() throws Exception {
        int i = 0;
        int k = 0;
        int value;
        mValues = new int[AspectraGlobals.eMaxSpectrumSize];
        try {
            File file;
            file = new File(mFileName);// TODO:here we need the whole name with path
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //StringBuffer stringBuffer = new StringBuffer();
            String line;

            // read data, first header
            while ((line = bufferedReader.readLine()) != null && (i < 5)) {
                i++;
            }

            //read real data
            while (((line = bufferedReader.readLine()) != null) && (k < SPK_CHR_FILE_DEFAULT_SIZE)) {

                try {
                    value = Integer.parseInt(line);
                    if(value < 0)
                        value = 0;
                } catch (NumberFormatException e) {
                    //Will Throw exception!
                    //do something! anything to handle the exception.
                    value = 0;
                }

                mValues[k] = value;

                i++;
                k++;
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return k;
    }

//    /**
//     * prepare content pf spectrum as String to saveStringToFile
//     * @param fileNameWithPath
//     * @return
//     * @throws Exception
//     */
//    public boolean writeAspFile(String fileNameWithPath) throws Exception {
//        boolean success = true;
//
//        File file = new File(fileNameWithPath);
//
//        FileOutputStream stream = null;
//        try{
//            stream = new FileOutputStream(file);
//            stream.write(mHeader.notesLength);
//            stream.write(mHeader.dataType);
//            stream.write(mHeader.dataLength);
//            stream.write(mHeader.notes.getBytes());
//            for(int i = 0; i < mHeader.dataLength; i++) {
//                stream.write(mValues[i]);
//            }
//
//        } catch (Exception e) {
////           // Log.e(TAG, "Error writing to file " + filename, e);
//            success = false;
//        } finally {
//            stream.close();
//        }
//        return success;
//    }


//    private void saveStringToFile(String text, File target) throws IOException {
//        FileOutputStream fos=new FileOutputStream(target);
//        OutputStreamWriter out=new OutputStreamWriter(fos);
//
//        out.write(text);
//        out.flush();
//        fos.getFD().sync();
//        out.close();
//    }


//	public boolean writeFile(Context context) throws Exception {
//        boolean success = true;
//        // create spectrum file name
//        String filename = UUID.randomUUID().toString() + ".asp";
//
//        // saveStringToFile the jpeg data to disk
//        FileOutputStream os = null;
//        try {
//            os = context.openFileOutput(filename, Context.MODE_PRIVATE);
//            os.write(data);
//        } catch (Exception e) {
//           // Log.e(TAG, "Error writing to file " + filename, e);
//            success = false;
//        } finally {
//            try {
//                if (os != null)
//                    os.close();
//            } catch (Exception e) {
//                //Log.e(TAG, "Error closing file " + filename, e);
//                success = false;
//            }
//        }
//        return success;
//    }

	public int readFile() throws Exception {
		return 0;
	}



    /**
     * Header for aspectra files.
     * Structure:
     * int32 notesLength // length of the String in header
     * int32 dataType; // 0 float32, 1 int32, 2 int16
     * int32 dataLength; // amount of data values, not bytes!
     * int32 index1
     * float32 waveLength1
     * int32index2
     * float32 waveLength2
     * String notes; // any content
     *
     * 11.03.2015 changed:
     * all data as ascii int or float, '.' is the difference
     * no internal notes
     */
    public class Header{

        public int notesLength;
        public int dataType;
        public int dataLength;
        public int normalizedToSize;
        public int index1;
        public float waveLength1;
        public int index2;
        public float waveLength2;
        public String notes;

    	public Header(){
            normalizedToSize = -1;

        }

        public String toString(){
            StringBuilder buffer = new StringBuilder();
            buffer.append(Integer.toString(notesLength));
            buffer.append("\n");
            buffer.append(Integer.toString(dataType));
            buffer.append("\n");
            buffer.append(Integer.toString(dataLength));
            buffer.append("\n");
            buffer.append(Integer.toString(normalizedToSize));
            buffer.append("\n");
            buffer.append(Integer.toString(index1));
            buffer.append("\n");
            buffer.append(Float.toString(waveLength1));
            buffer.append("\n");
            buffer.append(Integer.toString(index2));
            buffer.append("\n");
            buffer.append(Float.toString(waveLength2));
            buffer.append("\n");
            buffer.append(notes);
            buffer.append("\n");

            return buffer.toString();
        }
    }
}
