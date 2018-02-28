import java.util.*;

public class main {
    public static void main(String[] args) {
        List<Device> listToSort = new ArrayList<>();

        listToSort.add(new Device("Nexus 5", "3.2.1"));
        listToSort.add(new Device("Galaxy S7", "3.0.0"));
        listToSort.add(new Device("Nexus 4", "2.12"));
        listToSort.add(new Device("Galaxy S8", "2.12.1"));
        listToSort = sort(listToSort);

        displayDevices(listToSort);
    }

    private static List<Device> sort(List<Device> devices) {
        List<Device> outputList = new ArrayList<>();
        outputList.addAll(devices);
        List<String[]> versionsList = convertVersions(outputList);
        boolean devicesSwapped;

        for(int k = 0; k < versionsList.size() - 1; k++) {
            for(int i = 0; i < versionsList.size() - 1; i++) {
                devicesSwapped = false;
                for (int j = 0; j < versionsList.get(i).length; j++) {
                    if (!devicesSwapped) {
                        if(Integer.parseInt(versionsList.get(i)[j]) < Integer.parseInt(versionsList.get(i + 1)[j])) {
                            break;
                        }
                        if (Integer.parseInt(versionsList.get(i)[j]) > Integer.parseInt(versionsList.get(i + 1)[j])) {
                            outputList = swap(outputList, outputList.get(i), outputList.get(i + 1), i);
                            versionsList.clear();
                            versionsList = convertVersions(outputList);
                            devicesSwapped = true;
                        }
                    }
                }
            }
        }
        return outputList;
    }

    private static List<String[]> convertVersions(List<Device> outputList) {
        List<String[]> versionList = new ArrayList<>();

        for (Device d : outputList) {
            String[] versions = d.getVersion().split("[.]");
            versionList.add(versions);
        }
        return versionList;
    }

    private static List<Device> swap(List<Device> inputList, Device first, Device second, int index) {
        inputList.set(index, second);
        inputList.set((index + 1), first);
        return inputList;
    }

    private static void displayDevices(List<Device> listToSort) {
        for(Device d : listToSort) {
            System.out.print(d.getName());
            System.out.println(", version : " + d.getVersion());
            System.out.println("");
        }
    }
}
