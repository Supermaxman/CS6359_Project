package domain.product;

import java.util.List;

public class Painting extends Product {
	
	public String canvasType;
	public String paintType;
	public double length;
	public double width;
	private List<Painting> painting;
	
	public String getCanvasType() {
		return canvasType;
	}
	public void setCanvasType(String canvasType) {
		this.canvasType = canvasType;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPaintType() {
		return paintType;
	}
	public void setPaintType(String paintType) {
		this.paintType = paintType;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public List<Painting> getPainting() {
		return painting;
	}
	public void setPainting(List<Painting> painting) {
		this.painting = painting;
	}
	
	
	
	
}
