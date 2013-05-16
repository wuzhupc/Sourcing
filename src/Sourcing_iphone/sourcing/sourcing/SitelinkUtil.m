//
//  SitelinkUtil.m
//  Fellow
//
//  Created by wuzhu on 12-11-26.
//
//

#import "SitelinkUtil.h"
#import "StringUtil.h"
#import "DataInterfaceUtil.h"
#import "MobileInfoService.h"

@interface SitelinkUtil()
{
    UIViewController *_pvc;
    NSString *_newsid;
}
@end

@implementation SitelinkUtil


+(NSString *) isSiteLink:(NSString *)url
{
    if([StringUtil isEmptyStr:url])
        return nil;
    NSString *sitelinkstart = [DataInterfaceUtil getDataInterface:@"sitenewslinkpre"];
    NSRange range = [[url lowercaseString] rangeOfString:[sitelinkstart lowercaseString]];
    if(range.location == NSNotFound)
        return nil;
    return [url substringFromIndex:range.location+range.length];
}

-(void) processSiteLink:(NSString *)newsid parentViewController:(UIViewController *)pvc
{
    _pvc = pvc;
    _newsid = newsid;
    //TODO 获取服务端数据
}

-(void)serviceResult:(ResponseVO *)result
{
    //TODO
}
@end
