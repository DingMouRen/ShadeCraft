package com.dingmouren.shadecraft.utils

import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.Path
import android.graphics.RectF
import android.util.Log
import androidx.annotation.DrawableRes
import com.dingmouren.shadecraft.App
import com.dingmouren.shadecraft.R
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.core.MatOfPoint
import org.opencv.core.MatOfPoint2f
import org.opencv.core.Size
import org.opencv.imgproc.Imgproc

object  PathUtils {

   fun  getPathFromDrawable( @DrawableRes res: Int): List<Path> {
       val bgr = Utils.loadResource(App.context(), res)
       var mSource = Mat()
       Imgproc.cvtColor(bgr, mSource, Imgproc.COLOR_BGR2RGB)
       val tmp = mSource.clone()
       val gray = Mat()
       Imgproc.cvtColor(mSource, gray, Imgproc.COLOR_BGR2GRAY)
       Imgproc.GaussianBlur(gray, gray, Size(55.0, 55.0), 50.0, 50.0)
       val binary = Mat()
       Imgproc.threshold(gray, binary, 50.0, 255.0, Imgproc.THRESH_BINARY and Imgproc.THRESH_OTSU)

       val contours = mutableListOf<MatOfPoint>()
       val hierarchy = Mat()
       Imgproc.findContours(
           binary,
           contours,
           hierarchy,
           Imgproc.RETR_EXTERNAL,
           Imgproc.CHAIN_APPROX_NONE
       )

       val paths = mutableListOf<Path>()

       for (i in contours.indices) {
           val contour = MatOfPoint2f()
           contour.fromList(contours[i].toList())

           val path = Path()
           val points = contour.toArray()

           path.moveTo(points[0].x.toFloat(), points[0].y.toFloat())
           for (j in 1 until points.size) {
               path.lineTo(points[j].x.toFloat(), points[j].y.toFloat())
           }

           paths.add(path)

           contour.release()
       }

       gray.release()
       binary.release()
       hierarchy.release()
       tmp.release()

       return paths
   }

    fun scalePath(path: Path, scaleX: Float, scaleY: Float): Path {
        val matrix = Matrix()
        matrix.setScale(scaleX, scaleY)

        val newPath = Path()
        path.transform(matrix, newPath)

        val matrixBounds = android.graphics.Matrix()
        newPath.transform(matrixBounds)
        val bounds = RectF()
        newPath.computeBounds(bounds,true)


        return newPath
    }
}