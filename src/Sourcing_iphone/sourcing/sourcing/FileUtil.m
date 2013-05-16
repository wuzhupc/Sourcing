//
//  FileUtil.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "FileUtil.h"
#import "StringUtil.h"

@implementation FileUtil

/**
 *  读取项目文件夹assets文件夹中的文件内容
 */
+(NSString *)getAssetsFileContent:(NSString *)filename oftype:(NSString *)type
{
    if ([StringUtil isEmptyStr:filename]) {
        return nil;
    }
    //NSString *asset = [NSString stringWithFormat:@"%@\\%@",@"assets",filename];
    NSString *txtPath = [[NSBundle mainBundle] pathForResource:filename  ofType:type inDirectory:@"assets"];
    NSString *string = [[NSString  alloc] initWithContentsOfFile:txtPath encoding:NSUTF8StringEncoding error:nil];
    return string;
}

/**
 * Creates an file url (path) using location declaration and file extension.
 *
 * @param {Location} ENUM type
 * @param {NSString} Extension (e.g. @"jpg")
 *
 * @return {NSURL}
 */
+ (NSURL *)createFileURLForLocation:(FileLocation)location  withFileName:(NSString *)filename withExtension:(NSString *)extension
{
    
    return [NSURL fileURLWithPath:[FileUtil createFilePathForLocation:location withFileName:filename withExtension:extension]];
}

/**
 * Creates an file  path using location declaration and file extension.
 *
 * @param {Location} ENUM type
 * @param {NSString} Extension (e.g. @"jpg")
 *
 * @return {NSString}
 */
+ (NSString *)createFilePathForLocation:(FileLocation)location withFileName:(NSString *)filename withExtension:(NSString *)extension
{
    NSArray *paths          = nil;
    NSString *directory     = nil;
    
    switch (location) {
        case FileDirectoryCache:
            paths           = NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES);
            directory       = [paths objectAtIndex:0];
            break;
        case FileDirectoryTemp:
            directory       = NSTemporaryDirectory();
            break;
        case FileDirectoryDocuments:
            paths           = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
            directory       = [paths objectAtIndex:0];
            break;
        default:
            [NSException raise:@"Invalid location value" format:@"Location %u is invalid", location];
            break;
    }
    NSString *assetName     = [NSString stringWithFormat:@"%@.%@", filename, extension];
    //NSString *assetName     = [NSString stringWithFormat:@"%@.%@", [[NSProcessInfo processInfo] globallyUniqueString], extension];
    NSString *assetPath     = [directory stringByAppendingPathComponent:assetName];
    return assetPath;
}
@end
