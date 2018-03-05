package tokyo.kenshuhori_in.resouceCenterTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import tokyo.kenshuhori_in.SubMainInterface;

public class ResouceCenterMain implements SubMainInterface {

	public static void main(String[] args) {
		new ResouceCenterMain().execute();
	}

	List<VersionDto> VERSIONSINDB;
	List<VersionDto> REMOTELIST;
	public static final String CIU_PRODUCT_REGEX = "(CIU)(\\d{5})";

	@Override
	public void execute() {
		setResourceCenterMainData();
		VersionDto result = getLatestCiuVersionForAdmin();
		System.out.println("result : " + result.toString());
	}

	public void setResourceCenterMainData() {
		VERSIONSINDB = new ArrayList<VersionDto>();
		VERSIONSINDB.add(new VersionDto("CIU12000", false));
        VERSIONSINDB.add(new VersionDto("CIU12100", false));
        VERSIONSINDB.add(new VersionDto("CIU12200", false));
        VERSIONSINDB.add(new VersionDto("CIU12300", false));

        REMOTELIST = new ArrayList<VersionDto>();
        REMOTELIST.add(new VersionDto("CIU12000", true));
        REMOTELIST.add(new VersionDto("CIU12100", true));
        REMOTELIST.add(new VersionDto("CIU12200", true));
        REMOTELIST.add(new VersionDto("CIU12300", true));
	}

	// TODO:JUnitのテストコードを書いてパターンを網羅しよう
	public VersionDto getLatestCiuVersionForAdmin(){

        Comparator<VersionDto> cmp = (i, j) -> j.getVersion().compareTo( i.getVersion() );

        VersionDto remote = new VersionDto("CIU12300", true);
        VersionDto local = new VersionDto("CIU12300", false);

        if(cmp.compare(remote, local) < 0) {
        	System.out.println("成功");
        	return remote;
        } else {
        	System.out.println("失敗");
        	return local;
        }

//        VersionDto self = new VersionDto("CIU12200", false);
//
//        File[] localCiu = new File(".").listFiles(((dir, name) -> name.matches(CIU_PRODUCT_REGEX)));
//        List<String> updatePackage = Stream.of(localCiu).map( f -> f.getName() ).collect(Collectors.toList());
//
//        String latestInPackage = updatePackage.stream()
//        		.sorted(Comparator.reverseOrder())
//        		.findFirst()
//        		.get();
//
//        List<VersionDto> remoteCiuList = REMOTELIST;
//        VersionDto latestVersion = remoteCiuList.stream()
//        		.sorted(cmp)
//        		.findFirst()
//        		.filter(dto -> !latestInPackage.equals(dto.getVersion()))
//        		.orElse(self);
//
//        VersionDto a = remoteCiuList.stream()
//                .filter( remote -> cmp.compare(remote, latestVersion) < 0 )
//                .sorted( cmp  )
//                .findFirst()
//                .orElse(latestVersion);
//
//        return latestVersion;
    }
}
