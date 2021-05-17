package binarySearchTree;

public class Information<T> {
	protected T key;
	protected long indexInMasterFile;
	
	public Information(T key, long indexMasterFile) {
		this.key = key;
		this.indexInMasterFile = indexMasterFile;
	}

	public T geKey() {
		return key;
	}

	public void setKey(T information) {
		this.key = information;
	}

	public long getIndexInMasterFile() {
		return indexInMasterFile;
	}

	public void setIndexInMasterFile(long indexInMasterFile) {
		this.indexInMasterFile = indexInMasterFile;
	}
	@Override
	public String toString() {
		return String.format(" key %s indexMasterFile %d", key,indexInMasterFile);
	}
}
